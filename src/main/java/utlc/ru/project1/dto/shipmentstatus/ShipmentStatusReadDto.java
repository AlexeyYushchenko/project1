package utlc.ru.project1.dto.shipmentstatus;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;

import java.util.Map;

public record ShipmentStatusReadDto(
        Integer id,
        String name,
        Map<String, String> nameLocales,
        AuditingInfoDto auditingInfoDto
) {}