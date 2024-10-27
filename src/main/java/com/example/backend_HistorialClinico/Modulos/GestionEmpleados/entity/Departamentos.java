package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "departamentos")
public class Departamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // Constructor vacío
    public Departamentos() {
    }

    // Constructor con todos los atributos
    public Departamentos(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
