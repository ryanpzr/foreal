package com.example.foreal_project.controller;

import com.example.foreal_project.dto.HomeDto;
import com.example.foreal_project.model.Home;
import com.example.foreal_project.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/home")
public class HomeController {

    @Autowired
    private HomeService service;

    @GetMapping("/buscarDadosHome")
    public ResponseEntity<List<Home>> buscarDados() {
        List<Home> data = service.buscarDados();
        return ResponseEntity.ok().body(data);
    }

}
