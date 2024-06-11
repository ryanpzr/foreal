package com.example.foreal_project.model;

import com.example.foreal_project.dto.ComentarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.xml.sax.helpers.AttributesImpl;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Comentario")
@Table(name = "comentario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime time;

    private LocalDate date;

    private String autor;

    private String comentario;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comentario_id")
    private Home home;

    public Comentario(ComentarioDTO comentarioDTO ) {
        this.autor = comentarioDTO.autor();
        this.comentario = comentarioDTO.comentario();
        this.home = new Home(comentarioDTO.id());
        this.time = LocalTime.now();
        this.date = LocalDate.now();
    }
}
