package com.vgiardino.fulbito.security.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsuarioResponseDto {
    private int id;
    private String nombre;
    private String nombreUsuario;
    private String email;
    private List<String> roles;
}
