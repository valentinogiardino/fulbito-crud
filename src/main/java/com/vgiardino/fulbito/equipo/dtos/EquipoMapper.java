package com.vgiardino.fulbito.equipo.dtos;

import com.vgiardino.fulbito.equipo.Equipo;

import java.util.List;

public interface EquipoMapper {

    EquipoResponseDto toEquipoResponseDto(Equipo equipo);
    Equipo toEquipoFromReqCreate(EquipoReqCreateDto equipoReqCreateDto);
    Equipo toEquipoFromReqUpdate(EquipoReqUpdateDto equipoReqUpdateDto);
    List<EquipoResponseDto> toEquipoResponseDtoList(List<Equipo> equipoList);


}
