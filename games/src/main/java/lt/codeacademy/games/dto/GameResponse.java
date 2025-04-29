package lt.codeacademy.games.dto;

import java.time.LocalDate;

public record GameResponse(
        Long id,
        String name,
        String platform,
        LocalDate releaseDate,
        String summary,
        Double rating
) {}
