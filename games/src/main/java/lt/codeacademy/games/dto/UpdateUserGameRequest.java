package lt.codeacademy.games.dto;

import java.time.LocalDate;

public record UpdateUserGameRequest(
        LocalDate playedDate,
        String userReview,
        Float userRating
) {}
