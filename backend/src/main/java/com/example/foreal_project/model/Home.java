package com.example.foreal_project.model;

import com.example.foreal_project.dto.HomeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Home")
@Table(name = "home")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;

    private String titulo;

    private String conteudo;

    private Integer gostar;

    private LocalTime time;

    private LocalDate date;

    private String pathImg;

    private Boolean likeState;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();

    public Home(HomeDto dto) {
        this.autor = dto.autor();
        this.titulo = dto.titulo();
        this.conteudo = dto.conteudo();
        this.gostar = 1;
        this.likeState = false;
        this.pathImg = "/src/assets/img/like.png";
        this.time = LocalTime.now();
        this.date = LocalDate.now();
    }

    public Home(Long homeId) {
        this.id = homeId;
    }
}
