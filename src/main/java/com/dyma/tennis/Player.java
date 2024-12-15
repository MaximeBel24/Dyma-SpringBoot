package com.dyma.tennis;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record Player(
        @NotBlank(message = "First name is mandatory") String firstName,
        @NotBlank(message = "Last name is mandatory") String lastName,
        @PastOrPresent(message = "Birth date must be past or present") LocalDate birthDate,
        @Valid Rank rank) {
}
