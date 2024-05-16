package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Invoice;
import utlc.ru.project1.dto.invoice.InvoiceCreateUpdateDto;
import utlc.ru.project1.dto.invoice.InvoiceReadDto;

@Mapper
public interface InvoiceMapper {

    @Mapping(target = "auditingInfoDto", source = ".")
    InvoiceReadDto toDto(Invoice invoice);

    Invoice toEntity(InvoiceCreateUpdateDto dto);

    Invoice update(@MappingTarget Invoice invoice, InvoiceCreateUpdateDto dto);
}
