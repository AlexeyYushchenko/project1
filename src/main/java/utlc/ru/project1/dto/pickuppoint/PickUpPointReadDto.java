package utlc.ru.project1.dto.pickuppoint;

import utlc.ru.project1.dto.country.CountryReadDto;

import java.time.LocalDateTime;
import java.util.Map;

public record PickUpPointReadDto(
        Integer id,
        String name,
        CountryReadDto country,
        String address,
        Map<String, String> nameLocales,
        Map<String, String> addressLocales,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
