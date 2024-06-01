package com.vgiardino.fulbito.security.service;

import com.vgiardino.fulbito.security.dto.*;
import com.vgiardino.fulbito.security.entity.Usuario;

import java.text.ParseException;
import java.util.List;

public interface UsuarioService {
    JwtDto login(LoginCredentialsDto loginCredentialsDto);

    JwtDto refresh(JwtDto jwtDto) throws ParseException;

    Usuario add(UsuarioReqCreateDto usuarioReqCreateDto);

    List<Usuario> getAll();

    void delete(int id);

    Usuario getById(int id);

    Usuario getByUsername(String username);

    Usuario update(int id, UsuarioReqUpdateDto usuarioReqUpdateDto);
}
