package utlc.ru.project1.dto.clientstatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDateTime;

public record ClientStatusReadDto(

        Integer id,

        @NotNull (message = "Name is required")
        @Pattern(regexp = ".*\\S.*", message = "Status name must contain non-whitespace characters")
        @Size(min = 2, max = 50, message = "Status name must be between 2 and 50 characters")
        String name,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {}


