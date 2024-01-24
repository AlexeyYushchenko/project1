package utlc.ru.project1.dto.clientstatus;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;
import java.util.Map;

public record ClientStatusReadDto(
        Integer id,
        String name,
        Map<String, String> nameLocales,
        AuditingInfoDto auditingInfoDto
) {}


