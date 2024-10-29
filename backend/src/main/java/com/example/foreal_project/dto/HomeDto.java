package com.example.foreal_project.dto;

import org.springframework.web.multipart.MultipartFile;

public record HomeDto(String autor, String titulo, String conteudo, MultipartFile imagem) {
}
