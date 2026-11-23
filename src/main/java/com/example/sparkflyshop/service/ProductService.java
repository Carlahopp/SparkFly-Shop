package com.example.sparkflyshop.service;

import com.example.sparkflyshop.model.Product;
import com.example.sparkflyshop.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getStock() > 0)
                .toList();
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product input) {
        if (input == null) {
            throw new IllegalArgumentException("Product data is required.");
        }

        String name = StringUtils.trimToEmpty(input.getName());
        String category = StringUtils.trimToEmpty(input.getCategory());

        if (name.isBlank() || category.isBlank()) {
            throw new IllegalArgumentException("Name and category are required.");
        }

        if (input.getPrice() <= 0 || input.getStock() < 0) {
            throw new IllegalArgumentException("Price must be positive and stock cannot be negative.");
        }

        Product newProduct = new Product(0, name, category, input.getPrice(), input.getStock());
        return productRepository.save(newProduct);
    }
}
