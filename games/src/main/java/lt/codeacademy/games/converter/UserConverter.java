package lt.codeacademy.games.converter;

import lt.codeacademy.games.dto.CreateUserRequest;
import lt.codeacademy.games.dto.UserResponse;
import lt.codeacademy.games.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static User toEntity(CreateUserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setNickname(request.nickname());
        user.setCreatedAt(request.createdAt());
        user.setRole(request.role());
        return user;
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getNickname(),
                user.getCreatedAt(),
                user.getRole()
        );
    }
}
