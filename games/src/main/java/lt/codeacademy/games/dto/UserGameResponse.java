package lt.codeacademy.games.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({
        "id",
        "gameTitle",
        "gamePlatform",
        "gameReleaseDate",
        "gameRating",
        "userRating",
        "userReview",
        "playedDate",
        "userNickname"
})

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
