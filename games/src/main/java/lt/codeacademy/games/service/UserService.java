package lt.codeacademy.games.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.games.converter.UserConverter;
import lt.codeacademy.games.dto.CreateUserRequest;
import lt.codeacademy.games.dto.UpdateUserRequest;
import lt.codeacademy.games.dto.UserResponse;
import lt.codeacademy.games.entity.User;
import lt.codeacademy.games.exception.DuplicateUserException;
import lt.codeacademy.games.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(CreateUserRequest request) {
        userRepository.findByNickname(request.nickname())
                .ifPresent(user -> {
                    throw new DuplicateUserException("Nickname already taken");
                });
        User user = UserConverter.toEntity(request);
        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserConverter::toResponse)
                .toList();
    }

    public UserResponse patchUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        if (request.nickname() != null && !request.nickname().equals(user.getNickname())) {
            userRepository.findByNickname(request.nickname())
                    .ifPresent(existingUser -> {
                        throw new DuplicateUserException("Nickname already taken");
                    });
        }

        if (request.name() != null) user.setName(request.name());
        if (request.nickname() != null) user.setNickname(request.nickname());
        if (request.createdAt() != null) user.setCreatedAt(request.createdAt());
        if (request.role() != null) {
            user.setRole(request.role());
        }

        return UserConverter.toResponse(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public UserResponse getUserByNickname(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new EntityNotFoundException("User not found with nickname: " + nickname));
        return UserConverter.toResponse(user);
    }
}
