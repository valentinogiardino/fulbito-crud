package com.vgiardino.fulbito.equipo.dtos;

import com.vgiardino.fulbito.equipo.Equipo;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * Esta clase implementa la interface EquipoMapper para mapear un Equipo a sus dtos y viceversa
 * @author Valentino Giardino
 * @version 01/06/2024
 */
@Component
public class EquipoMapperImpl implements EquipoMapper{

    @Override
    public EquipoResponseDto toEquipoResponseDto(Equipo equipo) {
        return EquipoResponseDto.builder()
                .id(equipo.getId())
                .nombre(equipo.getNombre())
                .liga(equipo.getLiga())
                .pais(equipo.getPais())
                .build();
    }

    @Override
    public Equipo toEquipoFromReqCreate(EquipoReqCreateDto equipoReqCreateDto) {

        return Equipo.builder()
                .nombre(equipoReqCreateDto.getNombre())
                .liga(equipoReqCreateDto.getLiga())
                .pais(equipoReqCreateDto.getPais())
                .build();
    }

    @Override
    public Equipo toEquipoFromReqUpdate(EquipoReqUpdateDto equipoReqUpdateDto) {
        return Equipo.builder()
                .nombre(equipoReqUpdateDto.getNombre())
                .liga(equipoReqUpdateDto.getLiga())
                .pais(equipoReqUpdateDto.getPais())
                .build();
    }

    @Override
    public List<EquipoResponseDto> toEquipoResponseDtoList(List<Equipo> equipoList) {
        return equipoList
                .stream()
                .map(this::toEquipoResponseDto)
                .toList();
    }
}
