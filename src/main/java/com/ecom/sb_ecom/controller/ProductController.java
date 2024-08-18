package com.ecom.sb_ecom.controller;

import com.ecom.sb_ecom.config.AppConstants;
import com.ecom.sb_ecom.dto.ProductDto;
import com.ecom.sb_ecom.dto.ProductResponse;
import com.ecom.sb_ecom.model.Product;
import com.ecom.sb_ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/{categoryId}/product")
    public ResponseEntity<String> addProduct(@RequestBody Product product,@PathVariable Long categoryId){
        ProductDto productDto = productService.addProduct(product,categoryId);
        return new ResponseEntity<>("Product Added", HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(name="sortBy",defaultValue = AppConstants.SORT_PRODUCTS_BY,required = false) String sortBy,
            @RequestParam(name="sortOrder",defaultValue = AppConstants.SORT_DIR,required = false) String sortOrder

    ){

        ProductResponse ps = productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(ps,HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getAllProductsByCategory(@PathVariable Long categoryId){
        ProductResponse productResponse = productService.getAllProductsByCategory(categoryId);

        return new ResponseEntity<>(productResponse,HttpStatus.FOUND);
    }

    @GetMapping("/public/keyword/{keyword}/products")
    public ResponseEntity<ProductResponse> getAllProductsByKeyword(@PathVariable String keyword){
        ProductResponse productResponse = productService.getAllProductsByKeyword(keyword);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @PutMapping("admin/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable  Long productId,@RequestBody Product updatedProduct){
        ProductDto productDto = productService.updateProduct(productId,updatedProduct);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    @DeleteMapping("admin/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }
}
