package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.OrdenLaboratorio;

public interface OrdenLaboratorioRepository extends JpaRepository<OrdenLaboratorio, Integer>{
    List<OrdenLaboratorio> findByConsultaId(int consultaId);
    List<OrdenLaboratorio> findByFechaResultadoIsNull();
}
