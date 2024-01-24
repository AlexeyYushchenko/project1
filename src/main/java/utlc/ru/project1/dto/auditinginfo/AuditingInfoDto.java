package utlc.ru.project1.dto.auditinginfo;

import java.time.LocalDateTime;

public record AuditingInfoDto(
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {}
