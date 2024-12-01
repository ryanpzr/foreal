package com.example.foreal_project.service;

import com.example.foreal_project.dto.HomeDto;
import com.example.foreal_project.dto.ImgValueDTO;
import com.example.foreal_project.dto.LikeAndDeslikeDTO;
import com.example.foreal_project.dto.LikeStateDTO;
import com.example.foreal_project.model.Home;
import com.example.foreal_project.repository.HomeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
        String baseDir = "C:/foreal/frontend-vue/src/assets/imagesPosts/";

        String filePath = baseDir + imageData.getOriginalFilename();
        File destine = new File(filePath);

        imageData.transferTo(destine);

        Home home = new Home(dto);

        String fileSplitted = filePath.replaceAll(baseDir, "");
        home.setPathImagem(fileSplitted);

        return repository.save(home);
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
    public String updateImgValue(ImgValueDTO imgValueDTO) {
        repository.updateImgValue(imgValueDTO.id(), imgValueDTO.imgValue());
        return "";
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
