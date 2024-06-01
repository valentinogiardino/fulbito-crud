package com.vgiardino.fulbito.equipo;

import com.vgiardino.fulbito.exceptions.RepositoryException;
import com.vgiardino.fulbito.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceImplTest {

    @Mock
    private EquipoRepository equipoRepository;
    @InjectMocks
    private EquipoServiceImpl equipoService;

    private Equipo equipo;

    @BeforeEach
    void setUp() {
        equipo = Equipo.builder()
                .id(1L)
                .nombre("Equipo A")
                .liga("Liga A")
                .pais("Pais A")
                .build();
    }

    @Test
    void equipoService_GetAll_WhenEquiposExists() {
        Equipo equipo2 = Equipo.builder()
                .id(2L)
                .nombre("Equipo B")
                .liga("Liga B")
                .pais("Pais B")
                .build();

        List<Equipo> equipos = Arrays.asList(equipo, equipo2);
        when(equipoRepository.findAll()).thenReturn(equipos);

        List<Equipo> result = equipoService.getAll();
        assertEquals(equipos.size(), result.size());
        assertEquals(equipo, result.get(0));
        verify(equipoRepository, times(1)).findAll();

    }

    @Test
    void equipoService_GetAll_WhenEquiposNotExists() {
        when(equipoRepository.findAll()).thenReturn(Collections.emptyList());

        List<Equipo> result = equipoService.getAll();
        assertEquals(0, result.size());
        verify(equipoRepository, times(1)).findAll();

    }

    @Test
    void equipoService_GetById_WhenEquipoExists() {
        long equipoId = 1L;

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(equipo));

        Equipo result = equipoService.getById(equipoId);
        assertEquals(equipo, result);
        verify(equipoRepository, times(1)).findById(equipoId);

    }

    @Test
    void equipoService_GetById_WhenEquipoNotExists() {
        long equipoId = 1L;

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> equipoService.getById(equipoId));
        verify(equipoRepository, times(1)).findById(equipoId);

    }


    @Test
    void equipoService_GetAllByNombre_WhenEquipoExists() {
        String nombre = "Equipo";
        when(equipoRepository.findByNombreContainingIgnoreCase(nombre)).thenReturn(List.of(equipo));

        var result = equipoService.getByNombre(nombre);
        assertEquals(1, result.size());
        verify(equipoRepository, times(1)).findByNombreContainingIgnoreCase(nombre);

    }

    @Test
    void equipoService_GetAllByNombre_WhenEquipoNotExists() {
        String nombre = "Equipo";

        when(equipoRepository.findByNombreContainingIgnoreCase(nombre)).thenReturn(Collections.emptyList());

        var result = equipoService.getByNombre(nombre);

        assertEquals(0, result.size());
        verify(equipoRepository, times(1)).findByNombreContainingIgnoreCase(nombre);

    }

    @Test
    void equipoService_Create_WhenSuccess() {
        when(equipoRepository.save(equipo)).thenReturn(equipo);

        Equipo result = equipoService.create(equipo);
        assertEquals(equipo, result);
        verify(equipoRepository, times(1)).save(equipo);

    }

    @Test
    void equipoService_Create_WhenFailure() {
        when(equipoRepository.save(equipo)).thenThrow(new RuntimeException());

        assertThrows(RepositoryException.class, () -> equipoService.create(equipo));
        verify(equipoRepository, times(1)).save(equipo);

    }

    @Test
    void equipoService_Update_WhenEquipoExists() {
        long equipoId = 1L;
        Equipo updatedEquipo = Equipo.builder()
                .nombre("Equipo B")
                .liga("Liga B")
                .pais("Pais A")
                .build();

        updatedEquipo.setId(equipoId);

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(equipo));
        when(equipoRepository.save(updatedEquipo)).thenReturn(updatedEquipo);

        Equipo result = equipoService.update(equipoId, updatedEquipo);
        assertEquals(updatedEquipo, result);

    }


    @Test
    void equipoService_Update_WhenFailure() {
        long equipoId = 1L;
        Equipo updatedEquipo = Equipo.builder()
                .nombre("Equipo B")
                .liga("Liga B")
                .pais("Pais A")
                .build();

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(equipo));
        when(equipoRepository.save(updatedEquipo)).thenThrow(new RuntimeException());

        assertThrows(RepositoryException.class, () -> equipoService.update(equipoId, updatedEquipo));

    }


    @Test
    void equipoService_Update_WhenEquipoIsTheSame() {
        long equipoId = 1L;
        Equipo updatedEquipo = Equipo.builder()
                .nombre("Equipo A")
                .liga("Liga A")
                .pais("Pais A")
                .build();

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(equipo));

        Equipo result = equipoService.update(equipoId, updatedEquipo);
        assertEquals(updatedEquipo, result);
        verify(equipoRepository, times(0)).save(equipo);

    }


    @Test
    void equipoService_Update_WhenEquipoNotExists() {
        long equipoId = 1L;
        Equipo updatedEquipo = Equipo.builder()
                .nombre("Equipo B")
                .liga("Liga B")
                .pais("Pais A")
                .build();

        updatedEquipo.setId(equipoId);

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> equipoService.update(equipoId, updatedEquipo));
        verify(equipoRepository, times(1)).findById(equipoId);

    }

    @Test
    void equipoService_Delete_WhenEquipoExists() {
        long equipoId = 1L;

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(equipo));
        doNothing().when(equipoRepository).delete(equipo);

        assertAll(() -> equipoService.delete(equipoId));
        verify(equipoRepository, times(1)).delete(equipo);
    }

    @Test
    void testDeleteFailure() {
        long equipoId = 1L;

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> equipoService.delete(equipoId));
        verify(equipoRepository, times(0)).delete(equipo);

    }
}