package com.example.apiauthcrud.controller;

import com.example.apiauthcrud.dto.ProductDTO;
import com.example.apiauthcrud.model.Product;
import com.example.apiauthcrud.service.ProductService;
import com.example.apiauthcrud.dto.ApiResponseDTO;  // Ganti AuthResponseDTO dengan ApiResponseDTO
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create Product
    @PostMapping
    public ResponseEntity<ApiResponseDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);
        ApiResponseDTO response = new ApiResponseDTO("Product created successfully", product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Products
    @GetMapping
    public ResponseEntity<ApiResponseDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ApiResponseDTO response = new ApiResponseDTO("Successfully fetched all products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            ApiResponseDTO response = new ApiResponseDTO("Product found", product.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseDTO response = new ApiResponseDTO("Product not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct != null) {
            ApiResponseDTO response = new ApiResponseDTO("Product updated successfully", updatedProduct);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseDTO response = new ApiResponseDTO("Product not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            ApiResponseDTO response = new ApiResponseDTO("Product successfully deleted", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            ApiResponseDTO response = new ApiResponseDTO("Product not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
