package com.example.foreal_project.model;

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

}
