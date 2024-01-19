package utlc.ru.project1.dto.routestatus;

import java.time.LocalDateTime;
import java.util.Map;

public record RouteStatusReadDto(
        Integer id,
        String name,
        Map<String, String> nameLocales,

        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
