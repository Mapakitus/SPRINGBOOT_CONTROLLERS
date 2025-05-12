package com.pakitus.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "automoviles")
public class Automovil {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    private String marca;

    private Integer caballos;

    private Double precio;

    private Boolean electrico;

    // Constructor vacío
    public Automovil() {}

    // Constructor con parámetros
    public Automovil(String modelo, String marca, Integer caballos, Double precio, Boolean electrico) {
        this.modelo = modelo;
        this.marca = marca;
        this.caballos = caballos;
        this.precio = precio;
        this.electrico = electrico;
    }

    // Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getCaballos() {
        return caballos;
    }

    public void setCaballos(Integer caballos) {
        this.caballos = caballos;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getElectrico() {
        return electrico;
    }

    public void setElectrico(Boolean electrico) {
        this.electrico = electrico;
    }

    // Método toString
    @Override
    public String toString() {
        return "Automovil{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", caballos=" + caballos +
                ", precio=" + precio +
                ", electrico=" + electrico +
                '}';
    }
}
