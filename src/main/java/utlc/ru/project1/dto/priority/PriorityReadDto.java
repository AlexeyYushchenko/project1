package utlc.ru.project1.dto.priority;

import java.time.LocalDateTime;

public record PriorityReadDto(
        Integer id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
