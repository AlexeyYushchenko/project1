package utlc.ru.project1.dto.pickuppoint;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashMap;
import java.util.Map;

public record PickUpPointCreateUpdateDto(
        @NotNull(message = "validation.pickUpPoint.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.pickUpPoint.name.pattern")
        @Size(min = 2, max = 150, message = "validation.pickUpPoint.name.size")
        String name,

        @NotNull(message = "validation.pickUpPoint.country.required")
        Integer countryId,

        @NotNull(message = "validation.pickUpPoint.address.required")
        @Size(min = 2, max = 255, message = "validation.pickUpPoint.address.size")
        String address,

        Map<String, String> nameLocales,
        Map<String, String> addressLocales
) {
    public PickUpPointCreateUpdateDto {
        if (nameLocales == null) {
            nameLocales = new HashMap<>();
        }
        if (addressLocales == null) {
            addressLocales = new HashMap<>();
        }
    }
}