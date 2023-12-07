package utlc.ru.project1.dto.warehouse;

import utlc.ru.project1.dto.country.CountryReadDto;

import java.time.LocalDateTime;

public record WarehouseReadDto(
        Integer id,
        String name,
        CountryReadDto country,
        String address,
        String commentary,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}