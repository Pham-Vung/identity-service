package org.example.identityservice.DTO.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponse {
    boolean isAuthenticated;
}
