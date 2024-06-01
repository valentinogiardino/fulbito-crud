package com.vgiardino.fulbito.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UsuarioReqCreateDto {
    @NotBlank(message = "nombre obligatorio")
    private String nombre;
    @NotBlank(message = "nombre de usuario obligatorio")
    private String nombreUsuario;
    @Email(message = "dirección de email no válida")
    @NotBlank(message = "email obligatorio")
    private String email;
    @NotBlank(message = "contraseña obligatoria")
    private String password;
    private Set<String> roles;
}
