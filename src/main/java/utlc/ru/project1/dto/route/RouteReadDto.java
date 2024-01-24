package utlc.ru.project1.dto.route;

import utlc.ru.project1.database.entity.Country;
import utlc.ru.project1.database.entity.RouteStatus;
import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;
import utlc.ru.project1.dto.country.CountryReadDto;
import utlc.ru.project1.dto.routestatus.RouteStatusReadDto;

import java.time.LocalDateTime;

public record RouteReadDto(
        Long id,
        String identificationNumber,
        RouteStatusReadDto routeStatus,
        String transportType,
        Boolean isInternational,
        CountryReadDto countryOfDeparture,
        CountryReadDto countryOfDestination,
        String customsPost,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        AuditingInfoDto auditingInfoDto
) {
}