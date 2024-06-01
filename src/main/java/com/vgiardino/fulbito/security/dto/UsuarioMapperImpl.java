package com.vgiardino.fulbito.security.dto;

import com.vgiardino.fulbito.security.entity.Rol;
import com.vgiardino.fulbito.security.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsuarioMapperImpl implements UsuarioMapper{

    @Override
    public UsuarioResponseDto toUsuarioResponseDto(Usuario usuario) {
        return UsuarioResponseDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .nombreUsuario(usuario.getNombreUsuario())
                .email(usuario.getEmail())
                .roles(this.getRolNombre(usuario.getRoles()))
                .build();
    }

    @Override
    public List<UsuarioResponseDto> toUsuarioResponseDtoList(List<Usuario> usuarioList) {
        return usuarioList
                .stream()
                .map((this::toUsuarioResponseDto))
                .collect(Collectors.toList());
    }


    private List<String> getRolNombre(Set<Rol> roles){
        List<String> rolNombreList = new ArrayList<>();
        for (Rol rol:roles) {
            String rolNombre = rol.getRolNombre().toString().toLowerCase();
            rolNombreList.add(rolNombre.split("_")[1]);
        }
        return rolNombreList;
    }
}
