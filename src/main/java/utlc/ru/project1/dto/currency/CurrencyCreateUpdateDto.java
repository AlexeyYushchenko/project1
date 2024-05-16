package utlc.ru.project1.dto.currency;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record CurrencyCreateUpdateDto(
        @NotNull(message = "validation.currency.code.required")
        @Pattern(regexp = "^[A-Z]{3}$", message = "validation.currency.code.pattern")
        String code,

        @Size(max = 50, message = "validation.currency.name.size")
        String name,

        Boolean enabled
) {
}
