package utlc.ru.project1.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientStatusUpdateDto(
        @Pattern(regexp = ".*\\S.*", message = "Status name must contain non-whitespace characters")
        @Size(min = 2, max = 50, message = "Status name must be between 2 and 50 characters")
        String name) {}
