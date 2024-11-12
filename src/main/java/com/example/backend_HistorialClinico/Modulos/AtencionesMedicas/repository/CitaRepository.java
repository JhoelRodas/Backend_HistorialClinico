package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Cita;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;


public interface CitaRepository extends JpaRepository<Cita, Integer>{
    List<Cita> findByUser(User user);
    List<Cita> findByMedico_User_Id(int userId);

}
