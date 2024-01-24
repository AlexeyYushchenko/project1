package utlc.ru.project1.dto.manufacturer;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;
import utlc.ru.project1.dto.country.CountryReadDto;

public record ManufacturerReadDto(
        Integer id,
        String name,
        CountryReadDto country,
        String address,
        String commentary,
        AuditingInfoDto auditingInfoDto
) {
}
