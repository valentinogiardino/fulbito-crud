package com.vgiardino.fulbito.equipo.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquipoResponseDto {
    private Long id;
    private String nombre;
    private String liga;
    private String pais;
}
