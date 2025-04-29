package lt.codeacademy.games.converter;

import lt.codeacademy.games.dto.GameResponse;
import lt.codeacademy.games.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameConverter {

    public static GameResponse toResponse(Game game) {
        return new GameResponse(
                game.getId(),
                game.getName(),
                game.getPlatform(),
                game.getReleaseDate(),
                game.getSummary(),
                game.getRating()
        );
    }
}
