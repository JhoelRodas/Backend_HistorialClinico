package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "analisis_clinicos")
public class AnalisisClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private String rangoNormal;


    
    public AnalisisClinico() {
    }

    
    public AnalisisClinico(String nombre, String descripcion, String rangoNormal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rangoNormal = rangoNormal;
    }


    public int getId() {
        return id;
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

    public String getRangoNormal() {
        return rangoNormal;
    }


    public void setRangoNormal(String rangoNormal) {
        this.rangoNormal = rangoNormal;
    }


    
}
