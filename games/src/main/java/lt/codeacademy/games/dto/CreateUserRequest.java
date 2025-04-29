package lt.codeacademy.games.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lt.codeacademy.games.entity.UserRole;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateUserRequest(
        @Length(min = 3, max = 50)
        @NotBlank
        String name,

        @Length(min = 3, max = 50)
        @NotBlank
        String nickname,

        @NotNull
        @PastOrPresent
        LocalDate createdAt,

        @NotNull
        UserRole role
) {}
