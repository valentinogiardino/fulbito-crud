package com.vgiardino.fulbito.equipo;

import com.vgiardino.fulbito.exceptions.RepositoryException;
import com.vgiardino.fulbito.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService{

    private final EquipoRepository equipoRepository;

    @Override
    public List<Equipo> getAll() {
        return this.equipoRepository.findAll();
    }

    @Override
    public Equipo getById(long id) {
        return this.equipoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Equipo no encontrado"));
    }

    @Override
    public List<Equipo> getByNombre(String nombre) {
        return this.equipoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Equipo create(Equipo equipo) {
        try{
            return this.equipoRepository.save(equipo);

        }catch (Exception ex){
            throw new RepositoryException("400", HttpStatus.BAD_REQUEST, "La solicitud es invalida");
        }
    }

    @Override
    public Equipo update(long id, Equipo updatedEquipo) {
        Equipo existingEquipo = this.getById(id);
        updatedEquipo.setId(existingEquipo.getId());

        if(existingEquipo.equals(updatedEquipo)) {
            return existingEquipo;
        }
        try{
            return this.equipoRepository.save(updatedEquipo);

        }catch (Exception ex){
            throw new RepositoryException("400", HttpStatus.BAD_REQUEST, "La solicitud es invalida");
        }
    }

    @Override
    public void delete(long id) {
        Equipo equipo = this.getById(id);
        this.equipoRepository.delete(equipo);
    }


}
