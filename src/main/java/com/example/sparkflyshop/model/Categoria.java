package com.example.sparkflyshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nombre_categoria", nullable = false, length = 100)
    private String nombreCategoria;

    @Column(name = "estado")
    private Boolean estado = true;

    // Constructor vacío obligatorio para JPA
    public Categoria() {
    }

    // Constructor con dos parámetros
    public Categoria(String nombreCategoria, Boolean estado) {
        this.nombreCategoria = nombreCategoria;
        this.estado = estado;
    }
    public Categoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.estado = true;
    }

    // Getters y Setters
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
