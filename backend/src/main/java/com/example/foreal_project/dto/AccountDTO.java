package com.example.foreal_project.dto;

import org.springframework.web.multipart.MultipartFile;

public record AccountDTO(MultipartFile picture, String name, String email, String cellphone) {
}
