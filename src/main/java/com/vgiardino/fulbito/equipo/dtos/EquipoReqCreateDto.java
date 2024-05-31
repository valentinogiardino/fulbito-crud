package com.vgiardino.fulbito.equipo.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class EquipoReqCreateDto {
    @NotBlank(message = "El campo 'nombre' es obligatorio")
    private String nombre;
    @NotBlank(message = "El campo 'liga' es obligatorio")
    private String liga;
    @NotBlank(message = "El campo 'pais' es obligatorio")
    private String pais;
}
