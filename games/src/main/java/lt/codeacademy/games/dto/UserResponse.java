package lt.codeacademy.games.dto;

import lt.codeacademy.games.entity.UserRole;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String name,
        String nickname,
        LocalDate createdAt,
        UserRole role
) {}
