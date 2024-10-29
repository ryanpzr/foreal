package com.example.foreal_project.repository;

import com.example.foreal_project.dto.ImgValueDTO;
import com.example.foreal_project.model.Home;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Home h SET h.gostar = h.gostar + :likeParam WHERE h.id = :idNumber")
    int darLike(@Param("idNumber") Long idNumber, @Param("likeParam") Integer likeParam);

    @Query("SELECT h FROM Home h ORDER BY h.gostar DESC")
    List<Home> buscarPopular();

    @Query("SELECT h FROM Home h ORDER BY h.date DESC, h.time DESC")
    List<Home> buscarHome();

    @Query("SELECT h FROM Home h WHERE h.date >= :date")
    List<Home> buscarBreaking(@Param("date") LocalDate date);

    @Modifying
    @Query("UPDATE Home h SET h.pathImgLike = :imgValue WHERE h.id = :id")
    void updateImgValue(@Param("id") String id, @Param("imgValue") String imgValue);

    @Modifying
    @Query("UPDATE Home h SET h.likeState = :value WHERE h.id = :id")
    void updateLikeState(@Param("value") Boolean value, @Param("id") String id);
}
