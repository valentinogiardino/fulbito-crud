package com.vgiardino.fulbito.exceptions;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> runTimeExceptionHandler(RuntimeException ex){
        ErrorDTO error = ErrorDTO.builder().codigo("500").mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
        ErrorDTO error = ErrorDTO.builder().codigo(ex.getCode()).mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDTO> businessExceptionHandler(BusinessException ex){
        ErrorDTO error = ErrorDTO.builder().codigo(ex.getCode()).mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(value = RepositoryException.class)
    public ResponseEntity<ErrorDTO> repositoryExceptionHandler(RepositoryException ex){
        ErrorDTO error = ErrorDTO.builder().codigo(ex.getCode()).mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }


    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        ErrorDTO error = ErrorDTO.builder().codigo(ex.getCode()).mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<ErrorDTO> ioExceptionHandler(IOException ex){
        ErrorDTO error = ErrorDTO.builder().codigo("400").mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> illegalArgumentExceptionHandler(IllegalArgumentException ex){
        ErrorDTO error = ErrorDTO.builder().codigo("400").mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex){
        ErrorDTO error = ErrorDTO.builder().codigo("400").mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException ex){
        ErrorDTO error = ErrorDTO.builder().codigo("401").mensaje("Credenciales Incorrectas").build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDTO> handleAccessDeniedException(AccessDeniedException ex){
        ErrorDTO error = ErrorDTO.builder().codigo("403").mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        /*List<String> mensajes = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(err -> mensajes.add(err.getDefaultMessage()));
        ErrorDTO error = ErrorDTO.builder().codigo("400").mensaje(mensajes.stream().collect(Collectors.joining(","))).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);*/

        // La consigna pide devolver este mensaje ante un bad request
        ErrorDTO error = ErrorDTO.builder().codigo("400").mensaje("La solicitud es invalida").build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MalformedJwtException.class, UnsupportedJwtException.class, SignatureException.class})
    public ResponseEntity<ErrorDTO> jwtException(JwtException ex){
        ErrorDTO error = ErrorDTO.builder().codigo("401").mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }


}