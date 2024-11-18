package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Seguro;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services.SeguroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/seguros")
@RequiredArgsConstructor
public class SeguroController {
    private final SeguroService seguroService;

    @GetMapping
    public ResponseEntity<List<Seguro>> obtenerTodosLosSeguros() {
        return ResponseEntity.ok(seguroService.obtenerTodosLosSeguros());
    }

    @PostMapping
    public ResponseEntity<Seguro> crearSeguro(@RequestBody Seguro seguro) {
        return ResponseEntity.ok(seguroService.guardarSeguro(seguro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seguro> obtenerSeguroPorId(@PathVariable Integer id) {
        return seguroService.obtenerSeguroPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
