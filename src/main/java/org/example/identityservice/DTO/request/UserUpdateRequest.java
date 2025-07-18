package org.example.identityservice.DTO.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
