package com.vgiardino.fulbito.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RepositoryException extends RuntimeException{
    private String code;
    private HttpStatus status;

    public RepositoryException(String code, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }

}
