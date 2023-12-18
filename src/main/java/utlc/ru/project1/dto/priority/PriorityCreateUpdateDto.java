package utlc.ru.project1.dto.priority;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PriorityCreateUpdateDto(
        @NotNull(message = "validation.priority.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.priority.name.pattern")
        @Size(min = 2, max = 100, message = "validation.priority.name.size")
        String name,
        String description
) {
}
