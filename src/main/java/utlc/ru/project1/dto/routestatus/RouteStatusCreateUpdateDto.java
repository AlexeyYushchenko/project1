package utlc.ru.project1.dto.routestatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

public record RouteStatusCreateUpdateDto(
        @NotNull(message = "validation.routeStatus.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.routeStatus.name.pattern")
        @Size(min = 2, max = 100, message = "validation.routeStatus.name.size")
        String name,
        Map<String, String> nameLocales
) {
    public RouteStatusCreateUpdateDto {
        if (nameLocales == null) {
            nameLocales = new HashMap<>();
        }
    }
}