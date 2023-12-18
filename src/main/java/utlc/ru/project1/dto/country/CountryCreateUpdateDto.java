package utlc.ru.project1.dto.country;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public record CountryCreateUpdateDto(
        @NotNull(message = "validation.country.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.country.name.pattern")
        @Size(min = 2, max = 100, message = "validation.country.name.size")
        String name,

        @NotNull(message = "validation.country.code.required")
        @Size(max = 3, message = "validation.country.code.size")
        String code,

        @NotNull(message = "validation.country.isActive.required")
        Boolean isActive
) {
}