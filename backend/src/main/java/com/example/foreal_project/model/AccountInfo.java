package com.example.foreal_project.model;

import com.example.foreal_project.dto.AccountDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Account")
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AccountInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pathProfilePicture;
    private String name;
    private String email;
    private String cellphone;

    public AccountInfo(AccountDTO dto) {
        this.pathProfilePicture = "";
        this.name = dto.name();
        this.email = dto.email();
        this.cellphone = dto.cellphone();
    }

    public void setPathProfilePicture(String pathProfilePicture) {
        this.pathProfilePicture = pathProfilePicture;
    }
}
