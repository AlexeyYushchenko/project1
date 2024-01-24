package utlc.ru.project1.dto.pickuppoint;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;
import utlc.ru.project1.dto.country.CountryReadDto;

import java.util.Map;

public record PickUpPointReadDto(
        Integer id,
        String name,
        CountryReadDto country,
        String address,
        Map<String, String> nameLocales,
        Map<String, String> addressLocales,
        AuditingInfoDto auditingInfoDto
) {
}
