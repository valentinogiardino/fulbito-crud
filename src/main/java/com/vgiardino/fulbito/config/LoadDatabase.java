package com.vgiardino.fulbito.config;

import com.vgiardino.fulbito.security.dto.UsuarioReqCreateDto;
import com.vgiardino.fulbito.security.entity.Rol;
import com.vgiardino.fulbito.security.enums.RolNombre;
import com.vgiardino.fulbito.security.repository.RolRepository;
import com.vgiardino.fulbito.security.service.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {

/*
    private final EquipoRepository equipoRepository;
*/
    private final RolRepository rolRepository;
    private final UsuarioServiceImpl usuarioService;
/*
    COMENTADO DEBIDO A QUE LA CONSIGNA PIDE GENERAR LOS EQUIPOS INICIALES USANDO UN SCRIPT SQL

    @Bean
    @Transactional
    CommandLineRunner initEquipos() {
        return args -> {

            *//*Crear Equipos iniciales*//*
            equipoRepository.save(new Equipo("Real Madrid", "La Liga", "España"));
            equipoRepository.save(new Equipo("FC Barcelona", "La Liga", "España"));
            equipoRepository.save(new Equipo("Manchester United", "Premier League", "Inglaterra"));
            equipoRepository.save(new Equipo("Liverpool FC", "Premier League", "Inglaterra"));
            equipoRepository.save(new Equipo("Juventus FC", "Serie A", "Italia"));
            equipoRepository.save(new Equipo("AC Milan", "Serie A", "Italia"));
            equipoRepository.save(new Equipo("Bayern Munich", "Bundesliga", "Alemania"));
            equipoRepository.save(new Equipo("Borussia Dortmund", "Bundesliga", "Alemania"));
            equipoRepository.save(new Equipo("Paris Saint-Germain", "Ligue 1", "Francia"));
            equipoRepository.save(new Equipo("Olympique de Marseille", "Ligue 1", "Francia"));
            equipoRepository.save(new Equipo("FC Porto", "Primeira Liga", "Portugal"));
            equipoRepository.save(new Equipo("Sporting CP", "Primeira Liga", "Portugal"));
            equipoRepository.save(new Equipo("Ajax Amsterdam", "Eredivisie", "Países Bajos"));
            equipoRepository.save(new Equipo("Feyenoord", "Eredivisie", "Países Bajos"));
            equipoRepository.save(new Equipo("Celtic FC", "Scottish Premiership", "Escocia"));
            equipoRepository.save(new Equipo("Rangers FC", "Scottish Premiership", "Escocia"));
            equipoRepository.save(new Equipo("Galatasaray SK", "Süper Lig", "Turquía"));
            equipoRepository.save(new Equipo("Fenerbahçe SK", "Süper Lig", "Turquía"));
            equipoRepository.save(new Equipo("FC Zenit Saint Petersburg", "Premier League Rusa", "Rusia"));
            equipoRepository.save(new Equipo("Spartak Moscow", "Premier League Rusa", "Rusia"));
            equipoRepository.save(new Equipo("SL Benfica", "Primeira Liga", "Portugal"));
            equipoRepository.save(new Equipo("Besiktas JK", "Süper Lig", "Turquía"));
            equipoRepository.save(new Equipo("SSC Napoli", "Serie A", "Italia"));
            equipoRepository.save(new Equipo("Atlético Madrid", "La Liga", "España"));

        };
    }*/



    @Bean
    @Transactional
    CommandLineRunner initUsers() {
        return args -> {

            /*Crear Roles*/
            Rol adminRole = Rol.builder().rolNombre(RolNombre.ROLE_ADMIN).build();
            Rol userRole = Rol.builder().rolNombre(RolNombre.ROLE_USER).build();
            rolRepository.save(adminRole);
            rolRepository.save(userRole);

            /*Crear Usuarios Iniciales*/
            UsuarioReqCreateDto user = UsuarioReqCreateDto.builder()
                    .nombre("Test")
                    .nombreUsuario("test")
                    .password("12345")
                    .email("test@gmail.com")
                    .roles(new HashSet<>())
                    .build();

            UsuarioReqCreateDto admin = UsuarioReqCreateDto.builder()
                    .nombre("Admin")
                    .nombreUsuario("admin")
                    .password("12345")
                    .email("admin@gmail.com")
                    .roles(new HashSet<>(List.of("admin")))
                    .build();

            this.usuarioService.add(user);
            this.usuarioService.add(admin);
        };
    }
}