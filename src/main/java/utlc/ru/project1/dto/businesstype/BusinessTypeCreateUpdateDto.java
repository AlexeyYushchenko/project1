package utlc.ru.project1.dto.businesstype;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BusinessTypeCreateUpdateDto(
        @NotNull(message = "validation.businessType.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.businessType.name.pattern")
        @Size(min = 2, max = 100, message = "validation.businessType.name.size")
        String name,
        String description
) {
}
