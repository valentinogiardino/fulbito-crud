package com.vgiardino.fulbito.equipo.dtos;

import com.vgiardino.fulbito.equipo.Equipo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquipoMapperImplTest {

   private EquipoMapper equipoMapper;

    @BeforeEach
    void setUp() {
        equipoMapper = new EquipoMapperImpl();
    }

    @Test
    public void shouldMapEquipoReqCreateDtoToEquipo(){
        //Given
        EquipoReqCreateDto equipoReqCreateDto = new EquipoReqCreateDto(
                "Boca",
                "Liga Argentina",
                "Argentina");

        //When
        Equipo equipo = equipoMapper.toEquipoFromReqCreate(equipoReqCreateDto);

        //Then
        assertEquals(equipoReqCreateDto.getNombre(), equipo.getNombre());
        assertEquals(equipoReqCreateDto.getLiga(), equipo.getLiga());
        assertEquals(equipoReqCreateDto.getPais(), equipo.getPais());

    }

    @Test
    public void shouldThrowNullPointerExceptionWhenEquipoReqCreateDtoIsNull(){
        assertThrows(NullPointerException.class, () -> equipoMapper.toEquipoFromReqCreate(null));
    }

    @Test
    public void shouldMapEquipoReqUpdateDtoToEquipo(){
        EquipoReqUpdateDto equipoReqUpdateDto = new EquipoReqUpdateDto(
                "Boca",
                "Liga Argentina",
                "Argentina");

        Equipo equipo = equipoMapper.toEquipoFromReqUpdate(equipoReqUpdateDto);

        assertEquals(equipoReqUpdateDto.getNombre(), equipo.getNombre());
        assertEquals(equipoReqUpdateDto.getLiga(), equipo.getLiga());
        assertEquals(equipoReqUpdateDto.getPais(), equipo.getPais());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenEquipoReqUpdateDtoIsNull(){
        assertThrows(NullPointerException.class, () -> equipoMapper.toEquipoFromReqUpdate(null));
    }


    @Test
    public void shouldMapEquipoToEquipoResponseDto(){
        //Given
        Equipo equipo = Equipo.builder()
                .id(1L)
                .nombre("Equipo A")
                .liga("Liga A")
                .pais("Pais A")
                .build();

        //When
        EquipoResponseDto equipoResponseDto = equipoMapper.toEquipoResponseDto(equipo);

        //Then
        assertEquals(equipoResponseDto.getId(), equipo.getId());
        assertEquals(equipoResponseDto.getNombre(), equipo.getNombre());
        assertEquals(equipoResponseDto.getLiga(), equipo.getLiga());
        assertEquals(equipoResponseDto.getPais(), equipo.getPais());

    }

    @Test
    public void shouldThrowNullPointerExceptionWhenEquipoIsNull(){
        assertThrows(NullPointerException.class, () -> equipoMapper.toEquipoResponseDto(null));
    }


    @Test
    public void shouldMapEquipoListToEquipoResponseDtoList(){
        //Given
        Equipo equipo = Equipo.builder()
                .id(1L)
                .nombre("Equipo A")
                .liga("Liga A")
                .pais("Pais A")
                .build();

        Equipo equipo2 = Equipo.builder()
                .id(2L)
                .nombre("Equipo B")
                .liga("Liga B")
                .pais("Pais B")
                .build();

        List<Equipo> equipoList = List.of(equipo, equipo2);

        //When
        List<EquipoResponseDto> equipoResponseDtoList = equipoMapper.toEquipoResponseDtoList(equipoList);

        //Then
        assertEquals(equipoResponseDtoList.size(), equipoList.size());

        assertEquals(equipoResponseDtoList.get(0).getId(), equipo.getId());
        assertEquals(equipoResponseDtoList.get(0).getNombre(), equipo.getNombre());
        assertEquals(equipoResponseDtoList.get(0).getLiga(), equipo.getLiga());
        assertEquals(equipoResponseDtoList.get(0).getPais(), equipo.getPais());

        assertEquals(equipoResponseDtoList.get(1).getId(), equipo2.getId());
        assertEquals(equipoResponseDtoList.get(1).getNombre(), equipo2.getNombre());
        assertEquals(equipoResponseDtoList.get(1).getLiga(), equipo2.getLiga());
        assertEquals(equipoResponseDtoList.get(1).getPais(), equipo2.getPais());



    }
}