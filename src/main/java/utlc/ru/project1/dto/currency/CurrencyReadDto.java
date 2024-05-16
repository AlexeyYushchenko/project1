package utlc.ru.project1.dto.currency;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;

public record CurrencyReadDto(
        Integer id,
        String code,
        String name,
        Boolean enabled,
        AuditingInfoDto auditingInfoDto
) {
}
