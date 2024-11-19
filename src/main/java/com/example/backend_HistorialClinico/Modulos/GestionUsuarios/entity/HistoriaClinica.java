package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonIgnoreProperties({"historiaClinica", "hibernateLazyInitializer", "handler"})
    private User paciente;

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"historiaClinica", "ordenesLaboratorio", "hibernateLazyInitializer", "handler"})
    private List<Consulta> consultas;

    

    public HistoriaClinica() {}

    public HistoriaClinica(User paciente) {
        this.paciente = paciente;
    }

    public HistoriaClinica(User paciente, List<Consulta> consultas) {
        this.paciente = paciente;
        this.consultas = consultas;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPaciente() {
        return paciente;
    }

    public void setPaciente(User paciente) {
        this.paciente = paciente;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    

}
