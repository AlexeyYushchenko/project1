package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.InvoiceStatus;
import utlc.ru.project1.dto.invoicestatus.InvoiceStatusCreateUpdateDto;
import utlc.ru.project1.dto.invoicestatus.InvoiceStatusReadDto;

@Mapper
public interface InvoiceStatusMapper {
    InvoiceStatusReadDto toDto(InvoiceStatus entity);  // Entity to DTO
    InvoiceStatus toEntity(InvoiceStatusCreateUpdateDto dto);  // DTO to Entity
    InvoiceStatus update(@MappingTarget InvoiceStatus entity, InvoiceStatusCreateUpdateDto dto);
}