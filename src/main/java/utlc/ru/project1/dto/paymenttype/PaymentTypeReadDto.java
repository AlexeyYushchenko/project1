package utlc.ru.project1.dto.paymenttype;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;

import java.util.Map;

public record PaymentTypeReadDto(
        Integer id,
        String name,
        String description,
        Map<String, String> nameLocales,
        Map<String, String> descriptionLocales,
        AuditingInfoDto auditingInfoDto
) {
}
