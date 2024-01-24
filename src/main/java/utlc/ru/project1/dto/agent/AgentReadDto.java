package utlc.ru.project1.dto.agent;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;

public record AgentReadDto(
        Integer id,
        String name,
        String phone,
        String commentary,
        AuditingInfoDto auditingInfoDto
) {
}