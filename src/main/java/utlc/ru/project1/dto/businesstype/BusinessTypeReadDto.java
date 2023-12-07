package utlc.ru.project1.dto.businesstype;

import java.time.LocalDateTime;

public record BusinessTypeReadDto(
        Integer id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
