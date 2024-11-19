package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.OrdenLaboratorio;

public interface OrdenLaboratorioRepository extends JpaRepository<OrdenLaboratorio, Integer> {
    List<OrdenLaboratorio> findByConsultaId(int consultaId);

    List<OrdenLaboratorio> findByFechaResultadoIsNull();

    @Query("SELECT o FROM OrdenLaboratorio o WHERE o.consulta.user.id = :userId")
    List<OrdenLaboratorio> findByUserId(@Param("userId") int userId);
}
