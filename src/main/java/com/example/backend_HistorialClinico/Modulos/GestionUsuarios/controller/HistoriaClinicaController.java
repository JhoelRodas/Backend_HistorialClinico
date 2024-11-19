package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.controller;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Diagnostico;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Triaje;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.ConsultaService;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.TriajeService;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.HistoriaClinica;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/historias-clinicas")
public class HistoriaClinicaController {
    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    @Autowired
    private ConsultaService consultaService;

    /**
     * Crear una historia clínica para un paciente.
     */
    @PostMapping("/create")
    public ResponseEntity<HistoriaClinica> createHistoriaClinica(@RequestParam Integer userId) {
        HistoriaClinica historiaClinica = historiaClinicaService.createHistoriaClinica(userId);
        return ResponseEntity.ok(historiaClinica);
    }

    /**
     * Obtener la historia clínica de un paciente por su ID.
     */
    @GetMapping("/triaje/{pacienteId}")
    public ResponseEntity<HistoriaClinica> getHistoriaClinicaByPacienteId(@PathVariable Integer pacienteId) {
        return historiaClinicaService.getHistoriaClinicaByPacienteId(pacienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ------Para reportes------------
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Triaje>> getTriajesByUserId(@PathVariable int userId) {
        List<Triaje> triajes = historiaClinicaService.getTriajesByUserId(userId);
        return ResponseEntity.ok(triajes);
    }


    //obtener todas las diagnostico de un paciente relacionado a todas sus consultas
    @GetMapping("/usuario/{userId}/consultas-diagnosticos")
    public ResponseEntity<List<Map<String, Object>>> getConsultasYDiagnosticos(@PathVariable int userId) {
        List<Map<String, Object>> response = consultaService.getConsultasYDiagnosticosPorUsuario(userId);
        return ResponseEntity.ok(response);
    }

}
