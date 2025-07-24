package org.example.identityservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.identityservice.validator.DateOfBirthConstraint;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;

    private String firstName;
    private String lastName;

    @DateOfBirthConstraint(min = 18, message = "INVALID_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    private List<String> roles;
}
