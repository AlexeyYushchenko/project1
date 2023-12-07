package utlc.ru.project1.dto.invoicestatus;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record InvoiceStatusCreateUpdateDto(

        @Pattern(regexp = ".*\\S.*", message = "Status name must include at least one non-space character")
        @Size(min = 2, max = 100, message = "Status name must be between 2 and 100 characters")
        String name
) {
}