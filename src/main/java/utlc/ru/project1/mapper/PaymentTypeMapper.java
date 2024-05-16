package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.PaymentType;
import utlc.ru.project1.dto.paymenttype.PaymentTypeCreateUpdateDto;
import utlc.ru.project1.dto.paymenttype.PaymentTypeReadDto;

@Mapper
public interface PaymentTypeMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    PaymentTypeReadDto toDto(PaymentType paymentType);  // Entity to DTO

    PaymentType toEntity(PaymentTypeCreateUpdateDto createUpdateDto);  // DTO to Entity

    PaymentType update(@MappingTarget PaymentType paymentType, PaymentTypeCreateUpdateDto createUpdateDto);
}