package utlc.ru.project1.dto.auditinginfo;

import java.time.LocalDateTime;

public record AuditingInfo(
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {}
