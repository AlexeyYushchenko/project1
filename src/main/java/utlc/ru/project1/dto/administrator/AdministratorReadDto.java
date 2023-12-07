package utlc.ru.project1.dto.administrator;

import utlc.ru.project1.database.entity.Role;

import java.time.LocalDateTime;

public record AdministratorReadDto(
        Integer id,
        String username,
        String email,
        String firstname,
        String lastname,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
