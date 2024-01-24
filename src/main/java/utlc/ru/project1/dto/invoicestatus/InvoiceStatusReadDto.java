package utlc.ru.project1.dto.invoicestatus;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;

import java.util.Map;

public record InvoiceStatusReadDto(
        Integer id,
        String name,
        Map<String, String> nameLocales,
        AuditingInfoDto auditingInfoDto
) {}