package utlc.ru.project1.dto.priority;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record PriorityReadDto(
        Integer id,

        @NotNull(message = "Name is required")
        @Pattern(regexp = ".*\\S.*", message = "Status name must contain non-whitespace characters")
        @Size(min = 2, max = 100, message = "Status name must be between 2 and 100 characters")
        String name,
        String description,
        Instant createdAt,
        Instant modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
