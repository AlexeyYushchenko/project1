package utlc.ru.project1.dto.invoicestatus;

import java.time.LocalDateTime;
import java.util.Map;

public record InvoiceStatusReadDto(
        Integer id,
        String name,
        Map<String, String> nameLocales,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {}