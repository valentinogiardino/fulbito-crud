package com.vgiardino.fulbito.equipo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


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
