package utlc.ru.project1.dto.warehouse;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record WarehouseCreateUpdateDto(
        @NotNull(message = "validation.warehouse.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.warehouse.name.pattern")
        @Size(min = 2, max = 150, message = "validation.warehouse.name.size")
        String name,

        @NotNull(message = "validation.warehouse.country.required")
        Integer countryId,

        @NotNull(message = "validation.warehouse.address.required")
        @Size(min = 2, max = 255, message = "validation.warehouse.address.size")
        String address,

        String commentary
) {
}