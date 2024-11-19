package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "ordenes_laboratorio")
public class OrdenLaboratorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate fechaSolicitud;
    private LocalDate fechaResultado;

    // @Column(columnDefinition = "TEXT")
    // private String resultado;

    @ElementCollection // Permite almacenar una lista de elementos básicos
    @Column(columnDefinition = "TEXT")
    private List<String> resultados; // Cambia "resultado" a "resultados"
    private String observaciones;

    // Relación ManyToOne con Consulta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    @JsonIgnoreProperties({"ordenesLaboratorio", "receta", "historiaClinica", "hibernateLazyInitializer", "handler"})
    private Consulta consulta;

    // Relación ManyToOne con AnalisisClinico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analisis_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AnalisisClinico analisisClinico;

    public OrdenLaboratorio() {
    }

    public OrdenLaboratorio(LocalDate fechaSolicitud, Consulta consulta, AnalisisClinico analisisClinico) {
        this.fechaSolicitud = fechaSolicitud;
        this.consulta = consulta;
        this.analisisClinico = analisisClinico;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDate getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDate fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public List<String> getResultados() {
        return resultados;
    }

    public void setResultados(List<String> resultados) {
        this.resultados = resultados;
    }
    
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public AnalisisClinico getAnalisisClinico() {
        return analisisClinico;
    }

    public void setAnalisisClinico(AnalisisClinico analisisClinico) {
        this.analisisClinico = analisisClinico;
    }
}
