package utlc.ru.project1.dto.administrator;

import utlc.ru.project1.database.entity.Role;
import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;

public record AdministratorReadDto(
        Integer id,
        String username,
        String email,
        String firstname,
        String lastname,
        Role role,
        AuditingInfoDto auditingInfoDto
) {
}
