package utlc.ru.project1.dto.routestatus;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;
import java.util.Map;

public record RouteStatusReadDto(
        Integer id,
        String name,
        Map<String, String> nameLocales,
        AuditingInfoDto auditingInfoDto
) {
}
