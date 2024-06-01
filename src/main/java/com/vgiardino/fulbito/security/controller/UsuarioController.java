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

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<UsuarioResponseDto>> getAll();

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UsuarioResponseDto> add(@Valid @RequestBody UsuarioReqCreateDto usuarioReqCreateDto);

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Mensaje> delete(@RequestParam int id);

    @GetMapping("/getById")
    ResponseEntity<UsuarioResponseDto> getById(@RequestParam int id);
    @GetMapping("/getByUsername")
    ResponseEntity<UsuarioResponseDto> getByUsername(@RequestParam String username);

    @PutMapping("/update")
    ResponseEntity<UsuarioResponseDto> update(@Valid @RequestBody EditUsuario editUsuario);
}
