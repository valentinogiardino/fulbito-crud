package com.vgiardino.fulbito.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginCredentialsDto {
    @NotBlank(message = "username obligatorio")
    private String username;
    @NotBlank(message = "contraseña obligatoria")
    private String password;

}
