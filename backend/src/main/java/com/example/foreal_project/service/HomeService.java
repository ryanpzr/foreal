package com.example.foreal_project.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.foreal_project.dto.HomeDto;
import com.example.foreal_project.dto.LikeAndDeslikeDTO;
import com.example.foreal_project.dto.LikeStateDTO;
import com.example.foreal_project.model.Home;
import com.example.foreal_project.repository.HomeRepository;

import jakarta.transaction.Transactional;

@Service
public class HomeService {

    @Autowired
    private HomeRepository repository;

    public List<Home> buscarDados() {
        List<Home> data = repository.buscarHome();
        return data;
    }

    public Home postarDados(HomeDto dto) throws IOException {
        MultipartFile imageData = dto.imagem();
        String uploadDir = "/data/images/";

        String filename = imageData.getOriginalFilename();
        if(imageData.getOriginalFilename().contains(" ")) {
            filename = filename.replaceAll(" ", "");
        }

        String localFilePath = uploadDir + filename;
        imageData.transferTo(new File(localFilePath));
        String fileSplitted = localFilePath.replaceAll(uploadDir, "");
        Home home = new Home(dto);
        home.setPathImagem(fileSplitted);
        return repository.save(home);
    }

    public Resource buscarImagem(String filename) {
        java.nio.file.Path file = Paths.get("/data/images").resolve(filename);
        return new FileSystemResource(file);
    }

    @Transactional
    public int darLike(LikeAndDeslikeDTO like) {
        return repository.darLike(like.id(), like.likeParam());

    }

    public List<Home> buscarPopular() {
        return repository.buscarPopular();
    }

    public List<Home> buscarBreaking() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        return repository.buscarBreaking(sevenDaysAgo);
    }

    @Transactional
    public String updateLikeState(LikeStateDTO likeStateDTO) {
        repository.updateLikeState(likeStateDTO.likeState(), likeStateDTO.id());
        return "";
    }

    public List<Home> buscarPostPesquisado(String itemPesquisado) {
        return repository.buscarPostPesquisado(itemPesquisado);
    }
}
