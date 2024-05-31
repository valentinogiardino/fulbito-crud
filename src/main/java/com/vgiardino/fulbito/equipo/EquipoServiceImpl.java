package com.vgiardino.fulbito.equipo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipoServiceImpl implements EquipoService{

    private final EquipoRepository equipoRepository;

    @Override
    public List<Equipo> getAll() {
        return this.equipoRepository.findAll();
    }

    @Override
    public Equipo getById(long id) {
        return this.equipoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<Equipo> getByNombre(String nombre) {
        return this.equipoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Equipo create(Equipo equipo) {
        return this.equipoRepository.save(equipo);
    }

    @Override
    public Equipo update(long id, Equipo updatedEquipo) {
        Equipo existingEquipo = this.getById(id);
        updatedEquipo.setId(existingEquipo.getId());

        if(existingEquipo.equals(updatedEquipo)) {
            return existingEquipo;
        }

        return this.equipoRepository.save(updatedEquipo);
    }

    @Override
    public void delete(long id) {
        this.equipoRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Long> listIds) {
        this.equipoRepository.deleteAllById(listIds);
    }
}
