package utlc.ru.project1.dto.pickuppoint;

import utlc.ru.project1.dto.country.CountryReadDto;

import java.time.LocalDateTime;

public record PickUpPointReadDto(
        Integer id,
        String name,
        CountryReadDto country,
        String address,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
