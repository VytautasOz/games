package lt.codeacademy.games.service;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.games.converter.GameConverter;
import lt.codeacademy.games.dto.GameResponse;
import lt.codeacademy.games.entity.Game;
import lt.codeacademy.games.repository.GameRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public void loadCsv(String path) {
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                Game game = new Game();
                game.setName(line[0]);
                game.setPlatform(line[1].trim());
                game.setReleaseDate(LocalDate.parse(line[2], DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)));
                game.setSummary(line[3]);

                String ratingString = line[4];
                Double rating = null;
                if (!ratingString.equalsIgnoreCase("tbd")) {
                    rating = Double.parseDouble(ratingString);
                }
                game.setRating(rating);
                gameRepository.save(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<GameResponse> getAllGames() {
        List<Game> games = gameRepository.findAll(Sort.by(Sort.Order.desc("rating")));
        return games.stream()
                .map(GameConverter::toResponse)
                .collect(Collectors.toList());
    }

    public List<GameResponse> getGamesAboveRating(double rating) {
        List<Game> games = gameRepository.findByRatingGreaterThan(rating, Sort.by(Sort.Order.desc("rating")));
        return games.stream()
                .map(GameConverter::toResponse)
                .collect(Collectors.toList());
    }

    public List<GameResponse> getGamesByRatingAndPlatform(double rating, String platform) {
        List<Game> games = gameRepository.findByRatingGreaterThanAndPlatformContainingIgnoreCase(rating, platform, Sort.by(Sort.Order.desc("rating")));
        return games.stream()
                .map(GameConverter::toResponse)
                .collect(Collectors.toList());
    }
}
