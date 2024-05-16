package utlc.ru.project1.dto.servicetype;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashMap;
import java.util.Map;

public record ServiceTypeCreateUpdateDto(
        @NotNull(message = "validation.serviceType.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.serviceType.name.pattern")
        @Size(min = 2, max = 100, message = "validation.serviceType.name.size")
        String name,
        String description,
        Map<String, String> nameLocales,
        Map<String, String> descriptionLocales
) {
    public ServiceTypeCreateUpdateDto {
        if (nameLocales == null) {
            nameLocales = new HashMap<>();
        }
        if (descriptionLocales == null) {
            descriptionLocales = new HashMap<>();
        }
    }
}
