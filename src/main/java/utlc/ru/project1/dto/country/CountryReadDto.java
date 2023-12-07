package utlc.ru.project1.dto.country;

import java.time.LocalDateTime;

public record CountryReadDto(
        Integer id,
        String name,
        String code,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {}