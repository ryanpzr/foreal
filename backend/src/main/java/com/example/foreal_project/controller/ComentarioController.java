package com.example.foreal_project.controller;

import com.example.foreal_project.dto.ComentarioDTO;
import com.example.foreal_project.model.Comentario;
import com.example.foreal_project.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/postarComentario")
    public ResponseEntity<Comentario> postarComentario(@RequestBody ComentarioDTO dto) {
        Comentario data = service.postarComentario(dto);
        return ResponseEntity.ok().body(data);
    }

}
