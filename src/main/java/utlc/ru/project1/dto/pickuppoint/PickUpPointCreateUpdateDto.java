package utlc.ru.project1.dto.pickuppoint;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PickUpPointCreateUpdateDto(
        @NotNull(message = "Pick-Up Point name is required")
        @Pattern(regexp = ".*\\S.*", message = "Pick-up Point name must include at least one non-space character")
        @Size(min = 2, max = 150, message = "PickUpPoint's name must be between 2 and 150 characters")
        String name,

        @NotNull(message = "Country is required")
        Integer countryId,

        @NotNull(message = "Address is required")
        @Size(min = 2, max = 255, message = "Pick-up Point address must be between 2 and 150 characters")
        String address
) {
}
