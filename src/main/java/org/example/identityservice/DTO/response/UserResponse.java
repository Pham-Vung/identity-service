package org.example.identityservice.DTO.response;

import lombok.*;
import org.example.identityservice.entity.Role;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Set<Role> roles;
}
