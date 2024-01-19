package utlc.ru.project1.dto.route;

import utlc.ru.project1.database.entity.Country;
import utlc.ru.project1.database.entity.RouteStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RouteReadDto(
        Long id,
        String identificationNumber,
        RouteStatus status,
        String transportType,
        Boolean isInternational,
        Country countryOfDeparture,
        Country countryOfDestination,
        String customsPost,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,

        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}