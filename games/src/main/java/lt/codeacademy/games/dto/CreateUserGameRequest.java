package lt.codeacademy.games.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateUserGameRequest(
        @NotNull Long userId,
        @NotNull Long videoGameId,
        @NotNull LocalDate playedDate,
        String userReview,

        @DecimalMin(value = "0.0", message = "Rating must be at least 0")
        @DecimalMax(value = "10.0", message = "Rating cannot exceed 10")
        Float userRating
) {}