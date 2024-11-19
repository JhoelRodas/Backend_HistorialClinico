package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.AnalisisClinico;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.OrdenLaboratorio;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.AnalisisClinicoRepository;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.ConsultaRepository;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.OrdenLaboratorioRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenLaboratorioService {
    @Autowired
    private OrdenLaboratorioRepository ordenLaboratorioRepository;

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private AnalisisClinicoRepository analisisClinicoRepository;
    
    @Autowired
    private UserRepository userRepository;

    public List<OrdenLaboratorio> getOrdenesByConsultaId(int consultaId) {
        return ordenLaboratorioRepository.findByConsultaId(consultaId);
    }

    public OrdenLaboratorio registrarResultado(int id, List<String> resultados, String observaciones) {
        OrdenLaboratorio orden = ordenLaboratorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de laboratorio no encontrada"));

        orden.setResultados(resultados); // Asigna la lista de imágenes en Base64
        orden.setObservaciones(observaciones);
        orden.setFechaResultado(LocalDate.now());

        return ordenLaboratorioRepository.save(orden);
    }

    public OrdenLaboratorio createOrden(int consultaId, int analisisId, LocalDate fechaSolicitud) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada"));
        AnalisisClinico analisisClinico = analisisClinicoRepository.findById(analisisId)
                .orElseThrow(() -> new RuntimeException("Análisis clínico no encontrado"));

        OrdenLaboratorio ordenLaboratorio = new OrdenLaboratorio();
        ordenLaboratorio.setFechaSolicitud(fechaSolicitud);
        ordenLaboratorio.setConsulta(consulta);
        ordenLaboratorio.setAnalisisClinico(analisisClinico);

        return ordenLaboratorioRepository.save(ordenLaboratorio);
    }

    @Transactional(readOnly = true)
    public List<OrdenLaboratorio> getOrdenesPendientes() {
        return ordenLaboratorioRepository.findByFechaResultadoIsNull();
    }

    public List<OrdenLaboratorio> getOrdenesByUsuario(int userId) {
        return consultaRepository.findByUser(
                userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado")))
                .stream()
                .flatMap(consulta -> ordenLaboratorioRepository.findByConsultaId(consulta.getId()).stream())
                .collect(Collectors.toList());
    }

}
