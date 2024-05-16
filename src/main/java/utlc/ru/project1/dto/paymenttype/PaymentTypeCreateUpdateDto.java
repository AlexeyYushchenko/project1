package utlc.ru.project1.dto.paymenttype;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashMap;
import java.util.Map;

public record PaymentTypeCreateUpdateDto(
        @NotNull(message = "validation.paymentType.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.paymentType.name.pattern")
        @Size(min = 2, max = 100, message = "validation.paymentType.name.size")
        String name,
        String description,
        Map<String, String> nameLocales,
        Map<String, String> descriptionLocales
) {
    public PaymentTypeCreateUpdateDto {
        if (nameLocales == null) {
            nameLocales = new HashMap<>();
        }
        if (descriptionLocales == null) {
            descriptionLocales = new HashMap<>();
        }
    }
}
