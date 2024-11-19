package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.controller;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.OrdenLaboratorio;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.OrdenLaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/ordenes")
public class OrdenLaboratorioController {
    @Autowired
    private OrdenLaboratorioService ordenLaboratorioService;

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<OrdenLaboratorio>> getOrdenesByConsulta(@PathVariable int consultaId) {
        return ResponseEntity.ok(ordenLaboratorioService.getOrdenesByConsultaId(consultaId));
    }

    @PutMapping("/{id}/resultado")
    public ResponseEntity<OrdenLaboratorio> registrarResultado(
            @PathVariable int id,
            @RequestBody Map<String, Object> requestData) {
        List<String> resultados = (List<String>) requestData.get("resultados");
        String observaciones = (String) requestData.get("observaciones");

        OrdenLaboratorio orden = ordenLaboratorioService.registrarResultado(id, resultados, observaciones);
        return ResponseEntity.ok(orden);
    }

    @PostMapping
    public ResponseEntity<OrdenLaboratorio> createOrden(@RequestBody Map<String, Object> ordenData) {

        System.out.println(ordenData);
        int consultaId = (int) ordenData.get("consultaId");
        int analisisId = (int) ordenData.get("analisisId");
        LocalDate fechaSolicitud = LocalDate.now();

        OrdenLaboratorio nuevaOrden = ordenLaboratorioService.createOrden(consultaId, analisisId, fechaSolicitud);
        return ResponseEntity.ok(nuevaOrden);
    }

    @GetMapping("/pendientes")
    public List<OrdenLaboratorio> getOrdenesPendientes() {
        return ordenLaboratorioService.getOrdenesPendientes();
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<OrdenLaboratorio>> getOrdenesByUsuario(@PathVariable int userId) {
        List<OrdenLaboratorio> ordenes = ordenLaboratorioService.getOrdenesByUsuario(userId);
        return ResponseEntity.ok(ordenes);
    }

}
