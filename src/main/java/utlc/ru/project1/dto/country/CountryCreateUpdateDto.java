package utlc.ru.project1.dto.country;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public record CountryCreateUpdateDto(
        @NotNull(message = "Country name is required")
        @Pattern(regexp = ".*\\S.*", message = "Country name must include at least one non-space character")
        @Size(min = 2, max = 100, message = "Country name must be between 2 and 100 characters")
        String name,

        @NotNull(message = "Please enter the countryId code.")
        @Size(max = 3, message = "The code must be 3 letters long.")
        String code,

        @NotNull(message = "Please indicate whether the countryId's status is active")
        Boolean isActive
) {
}
