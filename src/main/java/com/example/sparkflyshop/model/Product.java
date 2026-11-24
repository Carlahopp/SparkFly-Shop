package com.example.sparkflyshop.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String name;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String description;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "imagen_url", length = 255)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria category;

    // Constructor vacío obligatorio para JPA
    public Product() {
    }

    // Constructor adaptado para mantener compatibilidad con tus pruebas/datos anteriores
    public Product(Integer id, String name, Categoria category, double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = BigDecimal.valueOf(price);
        this.stock = stock;
    }

    // Getters y Setters (conservando tus nombres originales)
    public long getId() {
        return id != null ? id : 0L;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price != null ? price.doubleValue() : 0.0;
    }

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }
}
