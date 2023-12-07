package utlc.ru.project1.dto.invoicestatus;

import java.time.LocalDateTime;

public record InvoiceStatusReadDto(
        Integer id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {}