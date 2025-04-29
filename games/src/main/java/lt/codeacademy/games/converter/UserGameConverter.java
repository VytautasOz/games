package lt.codeacademy.games.converter;

import lt.codeacademy.games.dto.CreateUserGameRequest;
import lt.codeacademy.games.dto.UserGameResponse;
import lt.codeacademy.games.entity.Game;
import lt.codeacademy.games.entity.User;
import lt.codeacademy.games.entity.UserGame;
import org.springframework.stereotype.Component;

@Component
public class UserGameConverter {

    public static UserGame toEntity(CreateUserGameRequest request) {
        UserGame userGame = new UserGame();
        userGame.setUserId(request.userId());
        userGame.setVideoGameId(request.videoGameId());
        userGame.setPlayedDate(request.playedDate());
        userGame.setUserReview(request.userReview());
        userGame.setUserRating(request.userRating());
        return userGame;
    }

    public static UserGameResponse toResponse(UserGame userGame, User user, Game game) {
        return new UserGameResponse(
                userGame.getId(),
                user.getNickname(),
                game.getName(),
                game.getPlatform(),
                game.getReleaseDate(),
                game.getRating(),
                userGame.getPlayedDate(),
                userGame.getUserReview(),
                userGame.getUserRating()
        );
    }
}
