package org.example.identityservice.DTO.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IntrospectRequest {
    String token;
}
