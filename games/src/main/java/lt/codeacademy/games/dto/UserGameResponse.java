package lt.codeacademy.games.dto;

import java.time.LocalDate;

public record UserGameResponse(
        Long id,
        String userNickname,
        String gameTitle,
        String gamePlatform,
        LocalDate gameReleaseDate,
        Double gameRating,
        LocalDate playedDate,
        String userReview,
        Float userRating
) {}
