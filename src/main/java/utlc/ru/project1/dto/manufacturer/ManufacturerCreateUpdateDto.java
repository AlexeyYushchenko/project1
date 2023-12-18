package utlc.ru.project1.dto.manufacturer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ManufacturerCreateUpdateDto(
        @NotNull(message = "validation.manufacturer.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.manufacturer.name.pattern")
        @Size(min = 2, max = 150, message = "validation.manufacturer.name.size")
        String name,

        @NotNull(message = "validation.manufacturer.country.required")
        Integer countryId,

        @NotNull(message = "validation.manufacturer.address.required")
        @Size(min = 2, max = 255, message = "validation.manufacturer.address.size")
        String address,

        String commentary
) {
}
