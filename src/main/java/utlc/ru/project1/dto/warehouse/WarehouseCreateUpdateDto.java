package utlc.ru.project1.dto.warehouse;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record WarehouseCreateUpdateDto(

        @NotNull(message = "Warehouse name is required")
        @Pattern(regexp = ".*\\S.*", message = "Warehouse name must include at least one non-space character")
        @Size(min = 2, max = 150, message = "Warehouse name must be between 2 and 150 characters")
        String name,

        @NotNull(message = "Country is required")
        Integer countryId,

        @NotNull(message = "address is required")
        @Size(min = 2, max = 255, message = "Warehouse address must be between 2 and 150 characters")
        String address,

        String commentary
) {
}