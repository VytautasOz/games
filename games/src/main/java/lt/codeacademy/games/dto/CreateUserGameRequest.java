package lt.codeacademy.games.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateUserGameRequest(
        @NotNull Long userId,
        @NotNull Long videoGameId,
        @NotNull LocalDate playedDate,
        String userReview,
        Float userRating
) {}