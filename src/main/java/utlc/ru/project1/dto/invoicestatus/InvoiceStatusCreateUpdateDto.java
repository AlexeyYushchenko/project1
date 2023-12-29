package utlc.ru.project1.dto.invoicestatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashMap;
import java.util.Map;

public record InvoiceStatusCreateUpdateDto(
        @NotNull(message = "validation.invoiceStatus.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.invoiceStatus.name.pattern")
        @Size(min = 2, max = 100, message = "validation.invoiceStatus.name.size")
        String name,
        Map<String, String> nameLocales
) {
        public InvoiceStatusCreateUpdateDto {
                if (nameLocales == null) {
                        nameLocales = new HashMap<>();
                }
        }
}