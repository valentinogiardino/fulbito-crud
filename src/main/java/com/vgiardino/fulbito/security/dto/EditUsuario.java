package com.vgiardino.fulbito.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Collections;
import java.util.Set;

@Data
public class EditUsuario {
    @NotNull(message = "id obligatorio")
    private int id;
    @NotBlank(message = "nombre obligatorio")
    private String nombre;
    @NotBlank(message = "nombre de usuario obligatorio")
    private String nombreUsuario;
    @Email(message = "dirección de email no válida")
    @NotBlank(message = "email obligatorio")
    private String email;
    private Set<String> roles;

    public Set<String> getRoles() {
        if (roles == null) {
            return Collections.emptySet();
        }
        return roles;
    }

}
