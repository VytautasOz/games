package lt.codeacademy.games.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.games.converter.UserGameConverter;
import lt.codeacademy.games.dto.CreateUserGameRequest;
import lt.codeacademy.games.dto.UpdateUserGameRequest;
import lt.codeacademy.games.dto.UserGameResponse;
import lt.codeacademy.games.entity.Game;
import lt.codeacademy.games.entity.User;
import lt.codeacademy.games.entity.UserGame;
import lt.codeacademy.games.exception.DuplicateUserGameException;
import lt.codeacademy.games.repository.GameRepository;
import lt.codeacademy.games.repository.UserGameRepository;
import lt.codeacademy.games.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGameService {

    private final UserGameRepository userGameRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserGame createUserGame(CreateUserGameRequest request) {

        userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + request.userId()));


        gameRepository.findById(request.videoGameId())
                .orElseThrow(() -> new EntityNotFoundException("Game not found with ID: " + request.videoGameId()));

        boolean exists = userGameRepository.existsByUserIdAndVideoGameId(request.userId(), request.videoGameId());
        if (exists) {
            throw new DuplicateUserGameException("User has already added this game.");
        }
        UserGame userGame = UserGameConverter.toEntity(request);
        return userGameRepository.save(userGame);
    }

    public List<UserGameResponse> getAllUserGames() {
        List<UserGame> userGames = userGameRepository.findAll();

        return userGames.stream().map(userGame -> {
            User user = userRepository.findById(userGame.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userGame.getUserId()));
            Game game = gameRepository.findById(userGame.getVideoGameId())
                    .orElseThrow(() -> new EntityNotFoundException("Game not found with id " + userGame.getVideoGameId()));

            return UserGameConverter.toResponse(userGame, user, game);
        }).toList();
    }

    public void deleteUserGame(Long id) {
        if (!userGameRepository.existsById(id)) {
            throw new EntityNotFoundException("UserGame not found with id: " + id);
        }
        userGameRepository.deleteById(id);
    }

    public UserGame updateUserGame(Long id, UpdateUserGameRequest request) {
        UserGame userGame = userGameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserGame not found with id: " + id));

        if (request.playedDate() != null) {
            userGame.setPlayedDate(request.playedDate());
        }
        if (request.userReview() != null) {
            userGame.setUserReview(request.userReview());
        }
        if (request.userRating() != null) {
            userGame.setUserRating(request.userRating());
        }

        return userGameRepository.save(userGame);
    }

}
