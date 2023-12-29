package utlc.ru.project1.dto.shipmentstatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashMap;
import java.util.Map;

public record ShipmentStatusCreateUpdateDto(
        @NotNull(message = "validation.shipmentStatus.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.shipmentStatus.name.pattern")
        @Size(min = 2, max = 100, message = "validation.shipmentStatus.name.size")
        String name,
        Map<String, String> nameLocales
) {
        public ShipmentStatusCreateUpdateDto {
                if (nameLocales == null) {
                        nameLocales = new HashMap<>();
                }
        }
}