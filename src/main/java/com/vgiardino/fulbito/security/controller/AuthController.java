package com.vgiardino.fulbito.security.controller;

import com.vgiardino.fulbito.security.dto.JwtDto;
import com.vgiardino.fulbito.security.dto.LoginCredentialsDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@RequestMapping("/auth")
@Tag(name = "Auth")
public interface AuthController {
    @PostMapping("/login")
    ResponseEntity<JwtDto> login(@Valid @RequestBody LoginCredentialsDto loginCredentialsDto);

    @PostMapping("/refresh")
    ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException;
}
