package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Currency;
import utlc.ru.project1.dto.currency.CurrencyCreateUpdateDto;
import utlc.ru.project1.dto.currency.CurrencyReadDto;

@Mapper()
public interface CurrencyMapper {

    @Mapping(target = "auditingInfoDto", source = ".")
    CurrencyReadDto toDto(Currency currency);

    Currency toEntity(CurrencyCreateUpdateDto dto);

    Currency update(@MappingTarget Currency currency, CurrencyCreateUpdateDto dto);
}
