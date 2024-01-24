package utlc.ru.project1.dto.route;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record RouteCreateUpdateDto(
        @NotNull(message = "validation.route.identificationNumber.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.route.identificationNumber.pattern")
        @Size(min = 2, max = 100, message = "validation.route.identificationNumber.size")
        String identificationNumber,

        @NotNull(message = "validation.route.routeStatus.required")
        Integer routeStatusId,

        @NotNull(message = "validation.route.transportType.required")
        String transportType,

        Boolean isInternational,

        @NotNull(message = "validation.route.countryOfDeparture.required")
        Integer countryOfDepartureId,

        @NotNull(message = "validation.route.countryOfDestination.required")
        Integer countryOfDestinationId,

        String customsPost,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate
        ) {
}