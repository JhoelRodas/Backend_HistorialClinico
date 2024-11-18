package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Seguro;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.SeguroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeguroService {
    private final SeguroRepository seguroRepository;

    public List<Seguro> obtenerTodosLosSeguros() {
        return seguroRepository.findAll();
    }

    public Optional<Seguro> obtenerSeguroPorId(Integer id) {
        return seguroRepository.findById(id);
    }

    public Seguro guardarSeguro(Seguro seguro) {
        return seguroRepository.save(seguro);
    }
}