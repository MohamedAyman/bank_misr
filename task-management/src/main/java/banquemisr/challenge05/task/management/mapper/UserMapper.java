package banquemisr.challenge05.task.management.mapper;

import banquemisr.challenge05.task.management.DTO.User;
import banquemisr.challenge05.task.management.entities.UserEntity;

public class UserMapper {

    // Convert User DTO to UserEntity
    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(RoleMapper.toEntity(user.getRole()));
        userEntity.setEmail(user.getEmail());
        userEntity.setCreatedAt(user.getCreatedAt());
        userEntity.setUpdatedAt(user.getUpdatedAt());

        return userEntity;
    }

    // Convert UserEntity to User DTO
    public static User toDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
//        user.setPassword(userEntity.getPassword());
        user.setRole(RoleMapper.toDTO(userEntity.getRole()));
        user.setEmail(userEntity.getEmail());
        user.setCreatedAt(userEntity.getCreatedAt());
        user.setUpdatedAt(userEntity.getUpdatedAt());

        return user;
    }
}
