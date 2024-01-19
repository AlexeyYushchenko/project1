package utlc.ru.project1.dto.priority;

import java.time.LocalDateTime;
import java.util.Map;

public record PriorityReadDto(
        Integer id,
        String name,
        String description,
        Map<String, String> nameLocales,
        Map<String, String> descriptionLocales,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
