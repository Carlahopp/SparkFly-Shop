package com.example.sparkflyshop.controller;

import com.example.sparkflyshop.model.Product;
import com.example.sparkflyshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/available")
    public List<Product> getAvailableProducts() {
        return productService.getAvailableProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        return ResponseEntity.of(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            Product created = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
}
