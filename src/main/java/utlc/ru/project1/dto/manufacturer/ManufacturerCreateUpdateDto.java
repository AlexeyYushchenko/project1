package utlc.ru.project1.dto.manufacturer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ManufacturerCreateUpdateDto(
        @NotNull(message = "Manufacturer name is required")
        @Pattern(regexp = ".*\\S.*", message = "Manufacturer name must include at least one non-space character")
        @Size(min = 2, max = 150, message = "Manufacturer's name must be between 2 and 150 characters")
        String name,

        @NotNull(message = "Country is required")
        Integer countryId,

        @NotNull(message = "Address is required")
        @Size(min = 2, max = 255, message = "Manufacturer's address must be between 2 and 150 characters")
        String address,

        String commentary
) {
}
