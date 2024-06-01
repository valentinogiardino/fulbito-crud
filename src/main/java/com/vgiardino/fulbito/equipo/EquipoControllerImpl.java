package com.vgiardino.fulbito.equipo;

import com.vgiardino.fulbito.equipo.dtos.EquipoMapper;
import com.vgiardino.fulbito.equipo.dtos.EquipoReqCreateDto;
import com.vgiardino.fulbito.equipo.dtos.EquipoReqUpdateDto;
import com.vgiardino.fulbito.equipo.dtos.EquipoResponseDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * Clase representa que implementa los métodos definidos en EquipoController
 * para gestionar las peticiones Http referidas a Equipos
 * author: Valentino Giardino
 * version: 01/06/2024
 */
@RestController
@RequiredArgsConstructor
public class EquipoControllerImpl implements EquipoController{

    //Inyección de dependencia con la notación @RequiredArgsConstructor
    private final EquipoService equipoService;
    private final EquipoMapper equipoMapper;
    @Override
    public ResponseEntity<List<EquipoResponseDto>> getAll() {
        List<Equipo> equipos = this.equipoService.getAll();
        return new ResponseEntity<>(this.equipoMapper.toEquipoResponseDtoList(equipos), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EquipoResponseDto> getById(long id) {
        Equipo equipo = this.equipoService.getById(id);
        return new ResponseEntity<>(this.equipoMapper.toEquipoResponseDto(equipo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EquipoResponseDto>> getAllByNombre(String nombre) {
        List<Equipo> equipoList = this.equipoService.getByNombre(nombre);
        return new ResponseEntity<>(this.equipoMapper.toEquipoResponseDtoList(equipoList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EquipoResponseDto> create(EquipoReqCreateDto equipoReqCreateDto) {
        Equipo equipo = this.equipoService.create(this.equipoMapper.toEquipoFromReqCreate(equipoReqCreateDto));
        return new ResponseEntity<>(this.equipoMapper.toEquipoResponseDto(equipo), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EquipoResponseDto> update(long id, EquipoReqUpdateDto equipoReqUpdateDto) {
        Equipo equipo = this.equipoService.update(id, this.equipoMapper.toEquipoFromReqUpdate(equipoReqUpdateDto));
        return new ResponseEntity<>(this.equipoMapper.toEquipoResponseDto(equipo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        this.equipoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
