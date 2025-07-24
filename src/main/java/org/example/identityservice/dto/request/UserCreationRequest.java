package org.example.identityservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.identityservice.validator.DateOfBirthConstraint;
import org.example.identityservice.validator.PasswordConstraint;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;

    @PasswordConstraint(min = 8, max = 15, message = "INVALID_PASSWORD")
    private String password;

    private String firstName;
    private String lastName;

    @DateOfBirthConstraint(min = 18, message = "INVALID_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
}
