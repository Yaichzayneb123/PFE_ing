package com.auth.security.Service;

import com.auth.security.DTO.*;
import com.auth.security.Entity.Societe;
import com.auth.security.Entity.UserMapper;
import com.auth.security.Repository.SocieteDAO;
import com.auth.security.config.JwtService;
import com.auth.security.Entity.Role;
import com.auth.security.Repository.UserDAO;
import com.auth.security.Entity.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthentificationService {
    private final UserDAO repository;
    @Autowired
    private SocieteDAO societeDAO;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(SocieteDTO request) {
        var societe = new Societe().builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .mat(request.getMat())
                .reg(request.getReg())
                .logo(request.getLogo())
                .tel(request.getTel())
                .site(request.getSite())
                .nomSociete(request.getNomSociete())
                .build();
        societeDAO.save(societe);
       Users adminSociete = UserMapper.toUser(societe);
        repository.save(adminSociete);


        var jwtToken = jwtService.generateToken(adminSociete);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
