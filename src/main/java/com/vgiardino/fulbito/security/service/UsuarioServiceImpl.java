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
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RolService rolService;

    private final JwtProvider jwtProvider;



    @Override
    public JwtDto login(LoginCredentialsDto loginCredentialsDto){
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentialsDto.getUsername(), loginCredentialsDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtDto(jwt);
    }
    @Override
    public JwtDto refresh(JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }

    @Override
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




    @Override
    public List<Usuario> getAll(){
        return this.usuarioRepository.findAll();
    }

    @Override
    public void delete(int id){
        Usuario usuario = this.getById(id);
        this.usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario getById(int id) {
        return this.usuarioRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No existe Usuario con id: " + id));
    }
    @Override
    public Usuario getByUsername(String username) {
        return this.usuarioRepository.findByNombreUsuario(username).
                orElseThrow(()->new ResourceNotFoundException("No existe Usuario con username: " + username));
    }
    @Override
    public Usuario update(int id, UsuarioReqUpdateDto usuarioReqUpdateDto) {
        Usuario usuario = this.getById(id);

        if(!Objects.equals(usuarioReqUpdateDto.getNombreUsuario(), usuario.getNombreUsuario()) && (usuarioRepository.existsByNombreUsuario(usuarioReqUpdateDto.getNombreUsuario())))
        {
            throw new BusinessException("P-301", HttpStatus.BAD_REQUEST, "ese nombre de usuario ya existe");
        }
        if(!Objects.equals(usuarioReqUpdateDto.getEmail(), usuario.getEmail()) && (usuarioRepository.existsByEmail(usuarioReqUpdateDto.getEmail())))
        {
            throw new BusinessException("P-302", HttpStatus.BAD_REQUEST, "ese email de usuario ya existe");
        }

        usuario.setNombreUsuario(usuarioReqUpdateDto.getNombreUsuario());
        usuario.setEmail(usuarioReqUpdateDto.getEmail());
        usuario.setNombre(usuarioReqUpdateDto.getNombre());

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER));
        if(usuarioReqUpdateDto.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN));
        usuario.setRoles(roles);

        return this.usuarioRepository.save(usuario);

    }
}
