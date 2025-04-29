package lt.codeacademy.games.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.games.dto.CreateUserGameRequest;
import lt.codeacademy.games.dto.UpdateUserGameRequest;
import lt.codeacademy.games.dto.UserGameResponse;
import lt.codeacademy.games.entity.UserGame;
import lt.codeacademy.games.service.UserGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usergame")
@RequiredArgsConstructor
public class UserGameController {

    private final UserGameService userGameService;

    @PostMapping("/create")
    public ResponseEntity<UserGame> createUserGame(@RequestBody @Valid CreateUserGameRequest request) {
        UserGame createdUserGame = userGameService.createUserGame(request);
        return ResponseEntity.ok(createdUserGame);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserGameResponse>> getAllUserGames() {
        return ResponseEntity.ok(userGameService.getAllUserGames());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUserGame(@RequestParam Long id) {
        userGameService.deleteUserGame(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<UserGame> updateUserGame(
            @RequestParam Long id,
            @RequestBody @Valid UpdateUserGameRequest request) {

        UserGame updatedUserGame = userGameService.updateUserGame(id, request);
        return ResponseEntity.ok(updatedUserGame);
    }
}
