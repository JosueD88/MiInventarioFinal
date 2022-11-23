package com.example.miinventario.models;

import android.security.keystore.StrongBoxUnavailableException;

public class Producto {

    private String nombre;
    private String descripcion;
    private String stock;


    public Producto() {

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

}
