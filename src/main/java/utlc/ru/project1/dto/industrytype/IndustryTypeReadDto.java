package utlc.ru.project1.dto.industrytype;

import java.time.LocalDateTime;

public record IndustryTypeReadDto(
        Integer id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
