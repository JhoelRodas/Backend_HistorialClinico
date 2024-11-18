package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services;




import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.AnalisisClinico;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.AnalisisClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalisisClinicoService {
    @Autowired
    private AnalisisClinicoRepository analisisClinicoRepository;

    public List<AnalisisClinico> getAllAnalisis() {
        return analisisClinicoRepository.findAll();
    }

    public AnalisisClinico createAnalisis(AnalisisClinico analisis) {
        return analisisClinicoRepository.save(analisis);
    }
}
