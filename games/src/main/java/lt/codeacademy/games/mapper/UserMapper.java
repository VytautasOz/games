package lt.codeacademy.games.mapper;

import lt.codeacademy.games.dto.CreateUserRequest;
import lt.codeacademy.games.dto.UserResponse;
import lt.codeacademy.games.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToEntity(CreateUserRequest request);
    UserResponse entityToDto(User user);
}
