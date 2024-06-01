package com.vgiardino.fulbito.security.controller;

import com.vgiardino.fulbito.security.dto.*;
import com.vgiardino.fulbito.security.entity.Usuario;
import com.vgiardino.fulbito.security.service.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsuarioControllerImpl implements UsuarioController{

    private final UsuarioServiceImpl usuarioService;
    private final UsuarioMapper usuarioMapper;

    @Override
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> usuarios = this.usuarioService.getAll();
        return new ResponseEntity<>(this.usuarioMapper.toUsuarioResponseDtoList(usuarios), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<UsuarioResponseDto> add(UsuarioReqCreateDto usuarioReqCreateDto){
        Usuario usuario = this.usuarioService.add(usuarioReqCreateDto);
        return new ResponseEntity<>(this.usuarioMapper.toUsuarioResponseDto(usuario), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        this.usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<UsuarioResponseDto> getById(int id) {
        Usuario usuario = this.usuarioService.getById(id);
        return new ResponseEntity<>(this.usuarioMapper.toUsuarioResponseDto(usuario), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> getByUsername(String username) {
        Usuario usuario = this.usuarioService.getByUsername(username);
        return new ResponseEntity<>(this.usuarioMapper.toUsuarioResponseDto(usuario), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> update(int id, UsuarioReqUpdateDto usuarioReqUpdateDto) {
        Usuario usuario = this.usuarioService.update(id, usuarioReqUpdateDto);
        return new ResponseEntity<>(this.usuarioMapper.toUsuarioResponseDto(usuario), HttpStatus.OK);
    }
}
