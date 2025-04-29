package lt.codeacademy.games.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.games.dto.GameResponse;
import lt.codeacademy.games.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/load")
    public String loadGames() {
        gameService.loadCsv("C:/Users/MSI INTEL4670/Desktop/Codeacademy/games/video_games.csv");
        return "Games loaded successfully";
    }

    @GetMapping
    public List<GameResponse> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/above")
    public List<GameResponse> getGamesAboveRating(@RequestParam double rating) {
        return gameService.getGamesAboveRating(rating);
    }

    @GetMapping("/filter")
    public List<GameResponse> getGamesByRatingAndPlatform(
            @RequestParam double rating,
            @RequestParam String platform) {
        return gameService.getGamesByRatingAndPlatform(rating, platform);
    }
}
