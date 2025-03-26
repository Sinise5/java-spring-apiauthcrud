package com.example.apiauthcrud.service;

import com.example.apiauthcrud.dto.ProductDTO;
import com.example.apiauthcrud.model.Product;
import com.example.apiauthcrud.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 📌 Create product & Evict cache 'allProducts' agar data terbaru bisa diambil
    @CacheEvict(value = "allProducts", allEntries = true)
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        return productRepository.save(product);
    }

    // 📌 Mengambil semua produk, caching untuk meningkatkan performa
    @Cacheable(value = "allProducts")
    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error fetching products: " + e.getMessage());
            throw e;
        }
    }

    // 📌 Mengambil produk berdasarkan ID, caching agar cepat diakses
    @Cacheable(value = "products", key = "#id")
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // 📌 Update product, lalu perbarui cache
    @CachePut(value = "products", key = "#id")
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        return productRepository.save(product);
    }

    // 📌 Hapus produk berdasarkan ID & Evict cache produk tersebut
    @CacheEvict(value = "products", key = "#id")
    public boolean deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
