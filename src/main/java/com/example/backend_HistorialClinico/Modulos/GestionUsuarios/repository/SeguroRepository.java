package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Seguro;

public interface SeguroRepository extends JpaRepository<Seguro, Integer> {
}