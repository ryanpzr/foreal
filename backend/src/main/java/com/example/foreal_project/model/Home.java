package com.example.foreal_project.model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.foreal_project.dto.HomeDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Boolean likeState;
    private String pathImgPost;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();

    public Home(HomeDto dto) {
        this.autor = dto.autor();
        this.titulo = dto.titulo();
        this.conteudo = dto.conteudo();
        this.pathImgPost = "";
        this.gostar = 1;
        this.likeState = false;
        this.time = LocalTime.now();
        this.date = LocalDate.now();
    }

    public Home(Long homeId) {
        this.id = homeId;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImgPost = pathImagem;
    }
}
