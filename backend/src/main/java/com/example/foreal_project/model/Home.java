package com.example.foreal_project.model;

import com.example.foreal_project.dto.HomeDto;
import jakarta.persistence.*;
import lombok.*;
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

    private Integer desgostar;

    public Home(HomeDto dto) {
        this.autor = dto.autor();
        this.titulo = dto.titulo();
        this.conteudo = dto.conteudo();
    }
}
