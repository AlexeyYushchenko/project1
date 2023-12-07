package utlc.ru.project1.dto.agent;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AgentCreateUpdateDto(
        @NotNull(message = "Agent name is required")
        @Pattern(regexp = ".*\\S.*", message = "Agent name must include at least one non-space character")
        @Size(min = 2, max = 50, message = "Agent name must be between 2 and 50 characters")
        String name,

        @Size(max = 20, message = "The code must be at maximum 20 letters long.")
        String phone,

        String commentary
) {
}
