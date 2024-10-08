package utlc.ru.project1.dto.country;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;

import java.util.Map;

public record CountryReadDto(
        Integer id,
        String name,
        String code,
        Boolean isActive,
        Map<String, String> nameLocales,
        AuditingInfoDto auditingInfoDto
) {}