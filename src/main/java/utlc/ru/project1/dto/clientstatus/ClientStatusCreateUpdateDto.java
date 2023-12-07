package utlc.ru.project1.dto.clientstatus;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientStatusCreateUpdateDto(
        @Pattern(regexp = ".*\\S.*", message = "Status name must include at least one non-space character")
        @Size(min = 2, max = 50, message = "Status name must be between 2 and 50 characters")
        String name
) {
}
