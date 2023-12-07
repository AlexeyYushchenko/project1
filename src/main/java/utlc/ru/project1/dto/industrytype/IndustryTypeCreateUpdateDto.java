package utlc.ru.project1.dto.industrytype;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record IndustryTypeCreateUpdateDto(

        @NotNull(message = "Industry Type name is required")
        @Pattern(regexp = ".*\\S.*", message = "Industry Type name must include at least one non-space character")
        @Size(min = 2, max = 50, message = "Industry Type name must be between 2 and 50 characters")
        String name,
        String description
) {
}
