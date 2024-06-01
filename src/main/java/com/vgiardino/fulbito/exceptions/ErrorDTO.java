package com.vgiardino.fulbito.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private String mensaje;
    private  String codigo;
}
