package com.example.foreal_project.repository;

import com.example.foreal_project.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface HomeRepository extends JpaRepository<Home, Long> {

}
