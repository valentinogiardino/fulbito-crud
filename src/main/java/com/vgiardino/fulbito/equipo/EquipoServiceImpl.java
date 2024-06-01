package com.vgiardino.fulbito.equipo;

import com.vgiardino.fulbito.exceptions.RepositoryException;
import com.vgiardino.fulbito.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase representa que implementa los métodos definidos en EquipoService
 * para efectuar las operaciones CRUD referidas a Equipos
 * @author Valentino Giardino
 * @version 01/06/2024
 */
@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService{

    //Inyección de dependencia con la notación @RequiredArgsConstructor
    private final EquipoRepository equipoRepository;
    /**
     * Comunicación con el repository para obtener todos los equipos de la base de datos.
     * @return una lista de los equipos cargados en la Base de datos
     * @author Valentino Giardino
     */
    @Override
    public List<Equipo> getAll() {
        return this.equipoRepository.findAll();
    }

    /**
     * Comunicación con el repository par obtener un equipo filtrado por su id.
     * @param id La pk del equipo que se quiere buscar
     * @return el equipo filtrado por id.
     * @throws ResourceNotFoundException cuando no existe un equipo con ese id
     * @author Valentino Giardino
     */
    @Override
    public Equipo getById(long id) {
        return this.equipoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Equipo no encontrado"));
    }

    /**
     * Comunicación con el repository para obtener todos los equipo que contienen un determinado nombre.
     * @param nombre El nombre de equipo con el que se quiere filtrar
     * @return el equipo filtrado por id.
     * @author Valentino Giardino
     */
    @Override
    public List<Equipo> getByNombre(String nombre) {
        return this.equipoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    /**
     * Comunicación con el repository para que inserte un nuevo equipo
     * @param equipo El equipo que se quiere guardar
     * @return el equipo creado
     * @throws RepositoryException cuando se produce un error en la inserción
     * @author Valentino Giardino
     */
    @Override
    public Equipo create(Equipo equipo) {
        try{
            return this.equipoRepository.save(equipo);

        }catch (Exception ex){
            throw new RepositoryException("400", HttpStatus.BAD_REQUEST, "La solicitud es invalida");
        }
    }
    /**
     * Comunicación con el repository para que actualice los datos de un equipo
     * @param id El id del equipo que se quiere actualizar
     * @param updatedEquipo Los nuevos datos del equipo a actualizar
     * @return el equipo actualizado
     * @throws ResourceNotFoundException cuando no se encuentra el equipo a actualizar
     * @throws RepositoryException cuando se produce un error en la inserción
     * @author Valentino Giardino
     */
    @Override
    public Equipo update(long id, Equipo updatedEquipo) {
        Equipo existingEquipo = this.getById(id);
        updatedEquipo.setId(existingEquipo.getId());

        //Evitar llamada a base de datos si no hay nada que cambiar
        if(existingEquipo.equals(updatedEquipo)) {
            return existingEquipo;
        }
        try{
            //actualización por merge
            return this.equipoRepository.save(updatedEquipo);

        }catch (Exception ex){
            throw new RepositoryException("400", HttpStatus.BAD_REQUEST, "La solicitud es invalida");
        }
    }
    /**
     * Comunicación con el repository para que eliminar un equipo
     * @param id El id del equipo que se quiere actualizar
     * @throws ResourceNotFoundException cuando no se encuentra el equipo a eliminar
     * @author Valentino Giardino
     */
    @Override
    public void delete(long id) {
        Equipo equipo = this.getById(id);
        this.equipoRepository.delete(equipo);
    }


}
