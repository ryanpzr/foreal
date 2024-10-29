package com.example.foreal_project.controller;

import com.example.foreal_project.dto.HomeDto;
import com.example.foreal_project.dto.ImgValueDTO;
import com.example.foreal_project.dto.LikeAndDeslikeDTO;
import com.example.foreal_project.dto.LikeStateDTO;
import com.example.foreal_project.model.Home;
import com.example.foreal_project.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/auth/home")
public class HomeController {

    @Autowired
    private HomeService service;

    @CrossOrigin(origins = "*")
    @PostMapping("postarDadosHome")
    public ResponseEntity<Home> postarDados(@ModelAttribute HomeDto dto) throws IOException {
        Home data = service.postarDados(dto);
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("updateImgValue")
    public ResponseEntity<String> updateImgValue(@RequestBody ImgValueDTO imgValueDTO) {
        String data = service.updateImgValue(imgValueDTO);
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("updateLikeState")
    public ResponseEntity<String> updateLikeState(@RequestBody LikeStateDTO likeStateDTO) {
        String data = service.updateLikeState(likeStateDTO);
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/like")
    public ResponseEntity<Integer> like(@RequestBody LikeAndDeslikeDTO like) {
        int data = service.darLike(like);
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/buscarDadosHome")
    public ResponseEntity<List<Home>> buscarDados() {
        List<Home> data = service.buscarDados();
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/buscarPopular")
    public ResponseEntity<List<Home>> buscarPopular() {
        List<Home> data = service.buscarPopular();
        return ResponseEntity.ok().body(data);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/buscarBreaking")
    public ResponseEntity<List<Home>> buscarBN() {
        List<Home> data = service.buscarBreaking();
        return ResponseEntity.ok().body(data);
    }

}
