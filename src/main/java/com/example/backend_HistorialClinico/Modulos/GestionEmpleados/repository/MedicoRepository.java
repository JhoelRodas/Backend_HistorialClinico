package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    // Encontrar un médico por el ID del usuario relacionado
    Optional<Medico> findByUserId(int userId);
}
