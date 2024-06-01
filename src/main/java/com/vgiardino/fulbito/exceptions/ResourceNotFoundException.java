package com.vgiardino.fulbito.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResourceNotFoundException extends RuntimeException{
    private String code;
    private HttpStatus status;
    public ResourceNotFoundException(String message){
        super(message);
        this.status = HttpStatus.NOT_FOUND;
        this.code = "404";
    }
}
