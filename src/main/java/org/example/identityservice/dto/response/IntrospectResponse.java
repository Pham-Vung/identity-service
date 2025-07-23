package org.example.identityservice.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IntrospectResponse {
    boolean isValid;
}
