package org.example.identityservice.dto.request;

import lombok.*;
import org.example.identityservice.validator.DateOfBirthConstraint;
import org.example.identityservice.validator.PasswordConstraint;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @PasswordConstraint(min = 8, max = 15, message = "INVALID_PASSWORD")
    private String password;

    private String firstName;
    private String lastName;

    @DateOfBirthConstraint(min = 18, message = "INVALID_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    private List<String> roles;
}
