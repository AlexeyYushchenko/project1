package utlc.ru.project1.dto.warehouse;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;
import utlc.ru.project1.dto.country.CountryReadDto;

public record WarehouseReadDto(
        Integer id,
        String name,
        CountryReadDto country,
        String address,
        String commentary,
        AuditingInfoDto auditingInfoDto
) {
}