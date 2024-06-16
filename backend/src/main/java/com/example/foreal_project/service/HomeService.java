package com.example.foreal_project.service;

import com.example.foreal_project.dto.HomeDto;
import com.example.foreal_project.dto.LikeAndDeslikeDTO;
import com.example.foreal_project.model.Home;
import com.example.foreal_project.repository.HomeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeRepository repository;

    public List<Home> buscarDados() {
        List<Home> data = repository.findAll();
        return data;
    }

    public Home postarDados(HomeDto dto) {
        Home data = repository.save(new Home(dto));
        return data;

    }

    @Transactional
    public int darLike(LikeAndDeslikeDTO like) {
        return repository.darLike(like.id(), like.likeParam());

    }

    public int darDeslike(LikeAndDeslikeDTO like) {
        return repository.darDeslike(like.id(), like.likeParam());
    }

    public List<Home> buscarPopular() {
        return repository.buscarPopular();
    }

    public List<Home> buscarBreaking() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        return repository.buscarBreaking(sevenDaysAgo);
    }
}
