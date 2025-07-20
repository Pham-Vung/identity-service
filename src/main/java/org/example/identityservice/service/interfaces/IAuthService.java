package org.example.identityservice.service.interfaces;

import com.nimbusds.jose.JOSEException;
import org.example.identityservice.DTO.request.AuthenticationRequest;
import org.example.identityservice.DTO.request.IntrospectRequest;
import org.example.identityservice.DTO.response.AuthenticationResponse;
import org.example.identityservice.DTO.response.IntrospectResponse;

import java.text.ParseException;

public interface IAuthService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;
}
