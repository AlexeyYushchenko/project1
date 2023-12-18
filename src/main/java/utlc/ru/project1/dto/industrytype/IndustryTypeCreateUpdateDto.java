package utlc.ru.project1.dto.industrytype;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record IndustryTypeCreateUpdateDto(
        @NotNull(message = "validation.industryType.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.industryType.name.pattern")
        @Size(min = 2, max = 50, message = "validation.industryType.name.size")
        String name,
        String description
) {
}
