package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Payment;
import utlc.ru.project1.dto.payment.PaymentCreateUpdateDto;
import utlc.ru.project1.dto.payment.PaymentReadDto;

@Mapper
public interface PaymentMapper {

    @Mapping(target = "auditingInfoDto", source = ".")
    PaymentReadDto toDto(Payment payment);

    Payment toEntity(PaymentCreateUpdateDto dto);

    Payment update(@MappingTarget Payment payment, PaymentCreateUpdateDto dto);
}
