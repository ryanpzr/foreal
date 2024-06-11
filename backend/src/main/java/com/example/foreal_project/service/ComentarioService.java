package com.example.foreal_project.service;

import com.example.foreal_project.dto.ComentarioDTO;
import com.example.foreal_project.model.Comentario;
import com.example.foreal_project.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    public Comentario postarComentario(ComentarioDTO dto) {
        return repository.save(new Comentario(dto));
    }
}
