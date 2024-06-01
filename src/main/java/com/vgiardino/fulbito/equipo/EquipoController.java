package com.vgiardino.fulbito.equipo;

import com.vgiardino.fulbito.equipo.dtos.EquipoReqCreateDto;
import com.vgiardino.fulbito.equipo.dtos.EquipoReqUpdateDto;
import com.vgiardino.fulbito.equipo.dtos.EquipoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/equipos")
@Tag(name="Equipos")
public interface EquipoController {

    @Operation(summary = "Obtiene todos los Equipos", description = "Devuelve la lista de todos los equipos de fútbol registrados.")
    @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Equipos listados exitosamente"),
    })
    @GetMapping
    ResponseEntity<List<EquipoResponseDto>> getAll();


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Obtiene un Equipo por id", description = "Devuelve la información del equipo correspondiente al ID proporcionado. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Not found - Equipo no encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<EquipoResponseDto> getById(@PathVariable
                                              @Parameter(name = "id", description = "Equipo id", example = "1")
                                              long id);




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Operation(summary = "Obtiene todos los Equipos por nombre", description = "Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipos listados exitosamente"),
    })
    @GetMapping("/buscar")
    ResponseEntity<List<EquipoResponseDto>> getAllByNombre(@RequestParam
                                                           @Parameter(name = "nombre", description = "Nombre de equipo", example = "FC")
                                                           String nombre);




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Operation(summary = "Crea un nuevo equipo", description = "Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipos creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "La solicitud es invalida"),
    })
    @PostMapping
    ResponseEntity<EquipoResponseDto> create(@RequestBody @Valid EquipoReqCreateDto equipoReqCreateDto);





    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Operation(summary = "Actualiza un equipo", description = "Devuelve la información actualizada del equipo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipos actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "La solicitud es invalida"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
    })
    @PutMapping("/{id}")
    ResponseEntity<EquipoResponseDto> update(@PathVariable
                                             @Parameter(name = "id", description = "Id del equipo a actualizar", example = "1")
                                             long id,
                                             @RequestBody
                                             @Parameter(name = "equipoReqUpdateDto", description = "Datos actualizados del equipo")
                                             @Valid EquipoReqUpdateDto equipoReqUpdateDto);





    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Operation(summary = "Elimina un equipo por id", description = "Busca un equipo por id y lo elimina de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipos listados exitosamente"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable
                             @Parameter(name = "id", description = "Id del equipo a eliminar", example = "1")
                             long id);



}
