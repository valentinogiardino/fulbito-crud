package com.vgiardino.fulbito.equipo;

import com.vgiardino.fulbito.equipo.dtos.EquipoReqCreateDto;
import com.vgiardino.fulbito.equipo.dtos.EquipoReqUpdateDto;
import com.vgiardino.fulbito.equipo.dtos.EquipoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/equipos")
public interface EquipoController {

    @GetMapping
    ResponseEntity<List<EquipoResponseDto>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<EquipoResponseDto> getById(@PathVariable long id);

    @GetMapping("/buscar")
    ResponseEntity<List<EquipoResponseDto>> getAllByNombre(@RequestParam String nombre);

    @PostMapping
    ResponseEntity<EquipoResponseDto> create(@RequestBody EquipoReqCreateDto equipoReqCreateDto);

    @PutMapping("/{id}")
    ResponseEntity<EquipoResponseDto> update(@PathVariable long id, @RequestBody EquipoReqUpdateDto equipoReqUpdateDto);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable long id);
}
