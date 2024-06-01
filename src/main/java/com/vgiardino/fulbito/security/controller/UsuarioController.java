package com.vgiardino.fulbito.security.controller;

import com.vgiardino.fulbito.security.dto.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuarios")
@Tag(name = "Usuarios")
public interface UsuarioController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<UsuarioResponseDto>> getAll();

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UsuarioResponseDto> add(@Valid @RequestBody UsuarioReqCreateDto usuarioReqCreateDto);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> delete(@PathVariable int id);

    @GetMapping("/{id}")
    ResponseEntity<UsuarioResponseDto> getById(@PathVariable int id);
    @GetMapping("/getByUsername")
    ResponseEntity<UsuarioResponseDto> getByUsername(@RequestParam String username);

    @PutMapping("/{id}")
    ResponseEntity<UsuarioResponseDto> update(@PathVariable int id, @Valid @RequestBody UsuarioReqUpdateDto usuarioReqUpdateDto);
}
