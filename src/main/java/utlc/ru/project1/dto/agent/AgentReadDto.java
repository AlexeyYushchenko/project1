package utlc.ru.project1.dto.agent;

import java.time.LocalDateTime;

public record AgentReadDto(
        Integer id,
        String name,
        String phone,
        String commentary,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}