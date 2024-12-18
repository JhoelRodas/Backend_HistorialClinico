package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.HistoriaClinica;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica,Integer> {
    Optional<HistoriaClinica> findByPacienteId(Integer pacienteId);
}
