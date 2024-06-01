package com.vgiardino.fulbito.equipo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EquipoReqCreateDto {
    @NotBlank(message = "La solicitud es invalida")
    private String nombre;
    @NotBlank(message = "La solicitud es invalida")
    private String liga;
    @NotBlank(message = "La solicitud es invalida")
    private String pais;
}
