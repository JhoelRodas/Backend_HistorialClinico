package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Triaje;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TriajeRepository extends JpaRepository<Triaje, Integer> {
    List<Triaje> findByUserId(Integer userId); 
}
