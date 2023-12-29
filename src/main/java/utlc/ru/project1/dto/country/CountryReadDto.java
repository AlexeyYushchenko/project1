package utlc.ru.project1.dto.country;

import java.time.LocalDateTime;
import java.util.Map;

public record CountryReadDto(
        Integer id,
        String name,
        String code,
        Boolean isActive,
        Map<String, String> nameLocales,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {}