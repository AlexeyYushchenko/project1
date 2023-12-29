package utlc.ru.project1.dto.clientstatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

public record ClientStatusReadDto(
        Integer id,
        String name,
        Map<String, String> nameLocales,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {}


