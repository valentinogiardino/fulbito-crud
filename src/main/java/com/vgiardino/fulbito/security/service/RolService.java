package com.vgiardino.fulbito.security.service;


import com.vgiardino.fulbito.exceptions.ResourceNotFoundException;
import com.vgiardino.fulbito.security.entity.Rol;
import com.vgiardino.fulbito.security.enums.RolNombre;
import com.vgiardino.fulbito.security.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public Rol getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre)
                .orElseThrow(()-> new ResourceNotFoundException("No existe el rol " + rolNombre));
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
