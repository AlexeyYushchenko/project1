package utlc.ru.project1.dto.client;

import utlc.ru.project1.dto.businesstype.BusinessTypeReadDto;
import utlc.ru.project1.dto.clientstatus.ClientStatusReadDto;
import utlc.ru.project1.dto.industrytype.IndustryTypeReadDto;
import java.time.LocalDateTime;

public record ClientReadDto(
        Integer id,
        String name,
        String fullName,
        ClientStatusReadDto clientStatus,
        BusinessTypeReadDto businessType,
        IndustryTypeReadDto industryType,
        String address,

        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}
