package com.auth.security.Service;

import com.auth.security.DTO.AuthenticationRequest;
import com.auth.security.DTO.AuthenticationResponse;
import com.auth.security.DTO.SocieteDTO;

public interface AuthentificationService {
    public SocieteDTO register(SocieteDTO request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
