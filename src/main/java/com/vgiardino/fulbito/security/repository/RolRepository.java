package com.vgiardino.fulbito.security.repository;

import com.vgiardino.fulbito.security.entity.Rol;
import com.vgiardino.fulbito.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
