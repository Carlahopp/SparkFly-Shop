package com.example.sparkflyshop.service;

import com.example.sparkflyshop.model.Categoria;
import com.example.sparkflyshop.model.Product;
import com.example.sparkflyshop.repository.CategoriaRepository;
import com.example.sparkflyshop.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoriaRepository categoriaRepository) {
        this.productRepository = productRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getStock() > 0)
                .toList();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product input) {
        if (input == null) {
            throw new IllegalArgumentException("Product data is required.");
        }

        String name = StringUtils.trimToEmpty(input.getName());
        String categoryName = (input.getCategory() != null)
                ? StringUtils.trimToEmpty(input.getCategory().getNombreCategoria())
                : "";

        if (name.isBlank() || categoryName.isBlank()) {
            throw new IllegalArgumentException("Name and category are required.");
        }

        if (input.getPrice() <= 0 || input.getStock() < 0) {
            throw new IllegalArgumentException("Price must be positive and stock cannot be negative.");
        }

        // Busca si la categoría ya existe en MySQL; si no existe, la crea automáticamente
        Categoria categoriaGuardada = categoriaRepository.findAll().stream()
                .filter(c -> c.getNombreCategoria().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElseGet(() -> categoriaRepository.save(new Categoria(categoryName, true)));

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setCategory(categoriaGuardada);
        newProduct.setPrice(input.getPrice());
        newProduct.setStock(input.getStock());
        newProduct.setDescription(input.getDescription());
        newProduct.setImageUrl(input.getImageUrl());

        return productRepository.save(newProduct);
    }
}
