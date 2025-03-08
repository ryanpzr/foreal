package com.example.foreal_project.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foreal_project.dto.HomeDto;
import com.example.foreal_project.dto.ImgValueDTO;
import com.example.foreal_project.dto.LikeAndDeslikeDTO;
import com.example.foreal_project.dto.LikeStateDTO;
import com.example.foreal_project.model.Home;
import com.example.foreal_project.service.HomeService;

@RestController
@RequestMapping("/auth/home")
public class HomeController {

    @Autowired
    private HomeService service;

    @CrossOrigin(origins = "*")
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        java.nio.file.Path file = Paths.get("/data/images").resolve(filename);
        Resource resource = new FileSystemResource(file);

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
    @GetMapping("/getPostSearched/{itemPesquisado}")
    public ResponseEntity<List<Home>> buscarPostPesquisado(@PathVariable String itemPesquisado) {
        List<Home> data = service.buscarPostPesquisado(itemPesquisado);
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
