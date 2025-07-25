package org.example.identityservice.config;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.example.identityservice.dto.request.IntrospectRequest;
import org.example.identityservice.dto.response.IntrospectResponse;
import org.example.identityservice.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.signerKey}")
    private String signerKey;

    private final IAuthService authService;
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            // check hiệu lực của token
            IntrospectResponse response = authService.introspect(
                    IntrospectRequest.builder()
                            .token(token)
                            .build()
            );

            if (!response.isValid()) {
                throw new JwtException("Invalid token");
            }
        } catch (ParseException | JOSEException e) {
            throw new JwtException(e.getMessage());
        }

        if (nimbusJwtDecoder == null) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
