package lt.codeacademy.games.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.games.dto.CreateUserRequest;
import lt.codeacademy.games.dto.UpdateUserRequest;
import lt.codeacademy.games.dto.UserResponse;
import lt.codeacademy.games.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest request) {
        userService.createUser(request);
        return ResponseEntity.ok("User created successfully");
    }

    @PatchMapping("/patch")
    public ResponseEntity<UserResponse> patchUser(@RequestParam Long id, @RequestBody @Valid UpdateUserRequest request) {
        UserResponse updatedUser = userService.patchUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/nickname")
    public ResponseEntity<UserResponse> getUserByNickname(@RequestParam String nickname) {
        UserResponse user = userService.getUserByNickname(nickname);
        return ResponseEntity.ok(user);
    }


}
