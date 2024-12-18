package banquemisr.challenge05.task.management.mapper;

import banquemisr.challenge05.task.management.DTO.Role;
import banquemisr.challenge05.task.management.entities.RoleEntity;

public class RoleMapper {
    // Convert Role DTO to RoleEntity
    public static RoleEntity toEntity(Role role) {
        if (role == null) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(role.getId());
        roleEntity.setRoleName(role.getRoleName());

        return roleEntity;
    }

    // Convert RoleEntity to Role DTO
    public static Role toDTO(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setRoleName(roleEntity.getRoleName());
        return role;
    }
}
