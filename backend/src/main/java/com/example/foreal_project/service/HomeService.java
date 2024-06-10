package com.example.foreal_project.service;

import com.example.foreal_project.dto.HomeDto;
import com.example.foreal_project.model.Home;
import com.example.foreal_project.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
