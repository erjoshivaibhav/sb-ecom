package com.ecom.sb_ecom.service;

import com.ecom.sb_ecom.dto.ProductDto;
import com.ecom.sb_ecom.dto.ProductResponse;
import com.ecom.sb_ecom.exceptionHandler.ResourceNotFoundException;
import com.ecom.sb_ecom.model.Category;
import com.ecom.sb_ecom.model.Product;
import com.ecom.sb_ecom.repository.CategoryRepository;
import com.ecom.sb_ecom.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProductDto addProduct(Product product, Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        product.setProductImage("default.png");

        Product addedProduct = productRepository.save(product);
        return modelMapper.map(addedProduct, ProductDto.class);
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> pageProducts = productRepository.findAll(pageDetails);
        System.out.println("***********************"+pageProducts);
        List<Product> products = pageProducts.getContent();
        List<ProductDto> productDtos = products.stream().map(p -> modelMapper.map(p, ProductDto.class)).toList();
        ProductResponse ps = new ProductResponse();
        ps.setContent(productDtos);
        ps.setPageNumber(pageProducts.getNumber());
        ps.setPageSize(pageProducts.getSize());
        ps.setTotalElements(pageProducts.getTotalElements());
        ps.setTotalPages((long) pageProducts.getTotalPages());
        ps.setLastPage(pageProducts.isLast());
        return ps;
    }

    @Override
    public ProductResponse getAllProductsByCategory(Long categoyId) {
        Category category = categoryRepository.findById(categoyId).orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
        List<Product> products = productRepository.findByCategoryOrderByProductPrice(category);
        List<ProductDto> productDtos = products.stream().map(p -> modelMapper.map(p, ProductDto.class)).toList();
        ProductResponse ps = new ProductResponse();
        ps.setContent(productDtos);
        return ps;
    }

    @Override
    public ProductResponse getAllProductsByKeyword(String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');
        List<ProductDto> productDtos = products.stream().map(product -> modelMapper.map(product, ProductDto.class)).toList();
        return ProductResponse.builder()
                .content(productDtos)
                .build();
    }

    @Override
    public ProductDto updateProduct(Long productId, Product updatedProduct) {
        Product productFromDb = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product ID not found"));
        productFromDb.setProductName(updatedProduct.getProductName());
        productFromDb.setProductDescription(updatedProduct.getProductDescription());
        productFromDb.setProductDiscount(updatedProduct.getProductDiscount());
        productFromDb.setProductPrice(updatedProduct.getProductPrice());
        productFromDb.setSpecialPrice(updatedProduct.getSpecialPrice());
        productFromDb.setProductImage(updatedProduct.getProductImage());
        productRepository.save(productFromDb);
        return modelMapper.map(productFromDb, ProductDto.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product productFromDb = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product ID not found"));
        productRepository.delete(productFromDb);

    }
}
