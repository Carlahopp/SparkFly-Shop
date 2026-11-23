package com.example.sparkflyshop.repository;

import com.example.sparkflyshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new LinkedHashMap<>();
    private long nextId = 1;

    public ProductRepository() {
        save(new Product(0, "folklore Cabin Ceramic Mug", "Drinkware", 299.00, 15));
        save(new Product(0, "Lover House Corduroy Pillow", "Home Decor", 450.00, 10));
        save(new Product(0, "reputation Crushed Velvet Crewneck", "Apparel", 750.00, 12));
        save(new Product(0, "The Tortured Poets Department Notebook", "Stationery", 220.00, 25));
        save(new Product(0, "Blank Space Heart Cake Ring", "Jewelry", 400.00, 0));
        save(new Product(0, "The Eras Tour Reusable Tote Bag", "Bags", 349.00, 18));
    }

    public synchronized List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public synchronized Optional<Product> findById(long id) {
        return Optional.ofNullable(products.get(id));
    }

    public synchronized Product save(Product product) {
        if (product.getId() == 0) {
            product.setId(nextId++);
        }
        products.put(product.getId(), product);
        return product;
    }
}
