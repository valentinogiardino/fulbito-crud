package com.vgiardino.fulbito.equipo;

import java.util.List;

public interface EquipoService {

    List<Equipo> getAll();
    Equipo getById(long id);
    List<Equipo> getByNombre(String nombre);
    Equipo create(Equipo equipo);
    Equipo update(long id, Equipo equipo);
    void delete(long id);

}
