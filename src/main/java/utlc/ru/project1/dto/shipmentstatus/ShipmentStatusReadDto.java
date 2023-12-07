package utlc.ru.project1.dto.shipmentstatus;

import java.time.LocalDateTime;

public record ShipmentStatusReadDto(
        Integer id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {}