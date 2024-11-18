package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.controller;


import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.AnalisisClinico;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.AnalisisClinicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/analisis")
public class AnalisisClinicoController {
    @Autowired
    private AnalisisClinicoService analisisClinicoService;

    @GetMapping
    public ResponseEntity<List<AnalisisClinico>> getAllAnalisis() {
        return ResponseEntity.ok(analisisClinicoService.getAllAnalisis());
    }

    @PostMapping
    public ResponseEntity<AnalisisClinico> createAnalisis(@RequestBody AnalisisClinico analisis) {
        return ResponseEntity.ok(analisisClinicoService.createAnalisis(analisis));
    }
}
