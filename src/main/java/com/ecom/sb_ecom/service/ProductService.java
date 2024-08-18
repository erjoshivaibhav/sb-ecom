package com.ecom.sb_ecom.service;

import com.ecom.sb_ecom.dto.ProductDto;
import com.ecom.sb_ecom.dto.ProductResponse;
import com.ecom.sb_ecom.model.Product;

public interface ProductService {

    ProductDto addProduct(Product product, Long categoryId);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse getAllProductsByCategory(Long categoryId);

    ProductResponse getAllProductsByKeyword(String keyword);

    ProductDto updateProduct(Long productId, Product updatedProduct);

    void deleteProduct(Long productId);
}
