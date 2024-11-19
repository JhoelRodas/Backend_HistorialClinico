package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Receta;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {

    Optional<Receta> findByConsultaId(Integer consultaId);

    // Optional<Receta> findByConsulta_Id(Integer consultaId);
}
