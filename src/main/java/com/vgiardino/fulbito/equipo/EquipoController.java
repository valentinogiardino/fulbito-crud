package com.vgiardino.fulbito.equipo;

import com.vgiardino.fulbito.equipo.dtos.EquipoReqCreateDto;
import com.vgiardino.fulbito.equipo.dtos.EquipoReqUpdateDto;
import com.vgiardino.fulbito.equipo.dtos.EquipoResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/equipos")
@Tag(name="Equipo")
public interface EquipoController {

    @GetMapping
    ResponseEntity<List<EquipoResponseDto>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<EquipoResponseDto> getById(@PathVariable long id);

    @GetMapping("/buscar")
    ResponseEntity<List<EquipoResponseDto>> getAllByNombre(@RequestParam String nombre);

    @PostMapping
    ResponseEntity<EquipoResponseDto> create(@RequestBody @Valid EquipoReqCreateDto equipoReqCreateDto);

    @PutMapping("/{id}")
    ResponseEntity<EquipoResponseDto> update(@PathVariable long id, @RequestBody @Valid EquipoReqUpdateDto equipoReqUpdateDto);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable long id);
}
