package com.vgiardino.fulbito.security.controller;


import com.vgiardino.fulbito.security.dto.*;
import com.vgiardino.fulbito.security.service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController{

    private final UsuarioServiceImpl usuarioService;

    @Override
    public ResponseEntity<JwtDto> login(LoginCredentialsDto loginCredentialsDto){
        return ResponseEntity.ok(usuarioService.login(loginCredentialsDto));
    }

    @Override
    public ResponseEntity<JwtDto> refresh(JwtDto jwtDto) throws ParseException {
        return ResponseEntity.ok(usuarioService.refresh(jwtDto));
    }

}
