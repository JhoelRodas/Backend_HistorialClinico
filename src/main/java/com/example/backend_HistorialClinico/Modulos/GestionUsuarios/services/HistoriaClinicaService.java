package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Triaje;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.TriajeRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.HistoriaClinica;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.HistoriaClinicaRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriaClinicaService {
     @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public HistoriaClinica createHistoriaClinica(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));

        if (historiaClinicaRepository.findByPacienteId(userId).isPresent()) {
            throw new RuntimeException("El usuario ya tiene una historia clínica asociada.");
        }

        HistoriaClinica historiaClinica = new HistoriaClinica(user);
        return historiaClinicaRepository.save(historiaClinica);
    }

    @Transactional(readOnly = true)
    public Optional<HistoriaClinica> getHistoriaClinicaByPacienteId(Integer pacienteId) {
        return historiaClinicaRepository.findByPacienteId(pacienteId);
    }




    //------para obtener reportes------- 

     @Autowired
    private TriajeRepository triajeRepository;

    public List<Triaje> getTriajesByUserId(int userId) {
        return triajeRepository.findByUserId(userId); // Llamada al repositorio
    }

    
    
}
