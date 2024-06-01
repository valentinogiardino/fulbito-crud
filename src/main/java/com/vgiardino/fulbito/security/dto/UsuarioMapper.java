package com.vgiardino.fulbito.security.dto;

import com.vgiardino.fulbito.security.entity.Usuario;

import java.util.List;

public interface UsuarioMapper {

    UsuarioResponseDto toUsuarioResponseDto(Usuario usuario);
    List<UsuarioResponseDto> toUsuarioResponseDtoList(List<Usuario> usuarioList);
}
