package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Cita;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Diagnostico;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.CitaRepository;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.ConsultaRepository;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.DiagnositicoRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.HistoriaClinica;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiagnositicoRepository diagnosticoRepository;

    @Transactional
    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> getConsultaById(int id) {
        return consultaRepository.findById(id);
    }

    @Transactional
    public Consulta createConsulta(int citaId, int userId, LocalTime horaInicio) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id " + citaId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));

        HistoriaClinica historiaClinica = user.getHistoriaClinica();
        if (historiaClinica == null) {
            throw new RuntimeException("El usuario no tiene una historia clínica asociada.");
        }

        if (!"Listo para Consulta".equals(cita.getEstado())) {
            throw new RuntimeException("La cita no está lista para consulta.");
        }

        Consulta consulta = new Consulta();
        consulta.setHistoriaClinica(historiaClinica);
        consulta.setCita(cita);
        consulta.setUser(user);
        consulta.setFechaConsulta(LocalDateTime.now());
        consulta.setHoraInicio(horaInicio);
        consulta.setMotivoConsulta("");

        cita.setEstado("En Consulta");
        citaRepository.save(cita);
        return consultaRepository.save(consulta);
    }

    // @Transactional
    // public Consulta finalizarConsulta(int consultaId, String motivoConsulta,
    // LocalTime horaFin) {

    // return consultaRepository.findById(consultaId)
    // .map(consulta -> {
    // consulta.setMotivoConsulta(motivoConsulta);
    // consulta.setHoraFin(horaFin);
    // return consultaRepository.save(consulta);
    // })
    // .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id " +
    // consultaId));
    // }

    @Transactional
    public Consulta finalizarConsulta(int consultaId, String motivoConsulta, LocalTime horaFin) {
        return consultaRepository.findById(consultaId)
                .map(consulta -> {
                    consulta.setMotivoConsulta(motivoConsulta);
                    consulta.setHoraFin(horaFin);

                    // Cambiar el estado de la cita a "finalizado"
                    Cita cita = consulta.getCita();
                    if (cita != null) {
                        cita.setEstado("finalizado");
                        citaRepository.save(cita); // Asegúrate de que el repositorio de citas esté inyectado
                    }

                    return consultaRepository.save(consulta);
                })
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id " + consultaId));
    }

    @Transactional
    public Consulta updateConsulta(int id, Consulta consultaDetails) {
        return consultaRepository.findById(id)
                .map(consulta -> {
                    consulta.setMotivoConsulta(consultaDetails.getMotivoConsulta());
                    consulta.setFechaConsulta(consultaDetails.getFechaConsulta());
                    consulta.setHoraInicio(consultaDetails.getHoraInicio());
                    consulta.setHoraFin(consultaDetails.getHoraFin());
                    return consultaRepository.save(consulta);
                })
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id " + id));
    }

    public void deleteConsulta(int id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Consulta no encontrada con id " + id);
        }
    }

    public List<Consulta> getConsultasByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));
        return consultaRepository.findByUser(user);
    }

    public List<Map<String, Object>> getConsultasYDiagnosticosPorUsuario(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));
    
        List<Consulta> consultas = consultaRepository.findByUser(user);
    
        return consultas.stream().map(consulta -> {
            Map<String, Object> consultaDiagnostico = new HashMap<>();
            consultaDiagnostico.put("consulta", Map.of(
                    "id", consulta.getId(),
                    "fechaConsulta", consulta.getFechaConsulta(),
                    "motivoConsulta", consulta.getMotivoConsulta()
            ));
    
            Diagnostico diagnostico = diagnosticoRepository.findByConsultaId(consulta.getId());
            if (diagnostico != null) {
                consultaDiagnostico.put("diagnostico", Map.of(
                        "id", diagnostico.getId(),
                        "tipoDiagnostico", diagnostico.getTipoDiagnostico(),
                        "sintomas", diagnostico.getSintomas(),
                        "observaciones", diagnostico.getObservaciones()
                ));
            } else {
                consultaDiagnostico.put("diagnostico", null);
            }
    
            return consultaDiagnostico;
        }).collect(Collectors.toList());
    }
}
