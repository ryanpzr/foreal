package com.example.foreal_project.controller;

import com.example.foreal_project.dto.HomeDto;
import com.example.foreal_project.dto.LikeAndDeslikeDTO;
import com.example.foreal_project.model.Home;
import com.example.foreal_project.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/home")
public class HomeController {

    @Autowired
    private HomeService service;

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PostMapping("postarDadosHome")
    public ResponseEntity<Home> postarDados(@RequestBody HomeDto dto) {
        Home data = service.postarDados(dto);
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PostMapping("/like")
    public ResponseEntity<Integer> like(@RequestBody LikeAndDeslikeDTO like) {
        int data = service.darLike(like);
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PostMapping("/deslike")
    public ResponseEntity<Integer> deslike(@RequestBody LikeAndDeslikeDTO like) {
        int data = service.darDeslike(like);
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/buscarDadosHome")
    public ResponseEntity<List<Home>> buscarDados() {
        List<Home> data = service.buscarDados();
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/buscarPopular")
    public ResponseEntity<List<Home>> buscarPopular() {
        List<Home> data = service.buscarPopular();
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/buscarBreaking")
    public ResponseEntity<List<Home>> buscarBN() {
        List<Home> data = service.buscarBreaking();
        return ResponseEntity.ok().body(data);
    }

}
