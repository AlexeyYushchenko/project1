package utlc.ru.project1.dto.agent;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AgentCreateUpdateDto(
        @NotNull(message = "validation.agent.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.agent.name.pattern")
        @Size(min = 2, max = 50, message = "validation.agent.name.size")
        String name,

        @Size(max = 20, message = "validation.agent.phone.size")
        String phone,

        String commentary
) {
}
