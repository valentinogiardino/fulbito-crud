package com.vgiardino.fulbito.security.service;

import com.vgiardino.fulbito.exceptions.BusinessException;
import com.vgiardino.fulbito.exceptions.ResourceNotFoundException;
import com.vgiardino.fulbito.security.dto.*;
import com.vgiardino.fulbito.security.entity.Rol;
import com.vgiardino.fulbito.security.entity.Usuario;
import com.vgiardino.fulbito.security.enums.RolNombre;
import com.vgiardino.fulbito.security.jwt.JwtProvider;
import com.vgiardino.fulbito.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RolService rolService;

    private final JwtProvider jwtProvider;



    public JwtDto login(LoginUsuario loginUsuario){
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtDto(jwt);
    }

    public JwtDto refresh(JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }

    public Usuario add(UsuarioReqCreateDto usuarioReqCreateDto){

        if(usuarioRepository.existsByNombreUsuario(usuarioReqCreateDto.getNombreUsuario()))
            throw new BusinessException("P-301", HttpStatus.BAD_REQUEST, "ese nombre de usuario ya existe");
        if(usuarioRepository.existsByEmail(usuarioReqCreateDto.getEmail()))
            throw new BusinessException("P-302", HttpStatus.BAD_REQUEST, "ese email de usuario ya existe");


        Usuario usuario = new Usuario(
                usuarioReqCreateDto.getNombre(),
                usuarioReqCreateDto.getNombreUsuario(),
                usuarioReqCreateDto.getEmail(),
                passwordEncoder.encode(usuarioReqCreateDto.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER));
        if(usuarioReqCreateDto.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN));
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }




    ///////////////CUSTOM////////
    public List<Usuario> getAll(){
        return this.usuarioRepository.findAll();
    }

    public Mensaje delete(int id){
        Usuario usuario = this.getById(id);
        this.usuarioRepository.delete(usuario);
        return new Mensaje("Usuario " + usuario.getNombreUsuario() + " eliminado");
    }


    public Usuario getById(int id) {
        return this.usuarioRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No existe Usuario con id: " + id));
    }

    public Usuario getByUsername(String username) {
        return this.usuarioRepository.findByNombreUsuario(username).
                orElseThrow(()->new ResourceNotFoundException("No existe Usuario con username: " + username));
    }

    public Usuario update(EditUsuario editUsuario) {
        Usuario usuario = this.getById(editUsuario.getId());

        if(!Objects.equals(editUsuario.getNombreUsuario(), usuario.getNombreUsuario()) && (usuarioRepository.existsByNombreUsuario(editUsuario.getNombreUsuario())))
        {
            throw new BusinessException("P-301", HttpStatus.BAD_REQUEST, "ese nombre de usuario ya existe");
        }
        if(!Objects.equals(editUsuario.getEmail(), usuario.getEmail()) && (usuarioRepository.existsByEmail(editUsuario.getEmail())))
        {
            throw new BusinessException("P-302", HttpStatus.BAD_REQUEST, "ese email de usuario ya existe");
        }

        usuario.setNombreUsuario(editUsuario.getNombreUsuario());
        usuario.setEmail(editUsuario.getEmail());
        usuario.setNombre(editUsuario.getNombre());

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER));
        if(editUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN));
        usuario.setRoles(roles);

        return this.usuarioRepository.save(usuario);

    }
}
