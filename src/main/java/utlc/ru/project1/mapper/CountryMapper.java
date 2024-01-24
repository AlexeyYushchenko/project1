package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Country;
import utlc.ru.project1.dto.country.CountryCreateUpdateDto;
import utlc.ru.project1.dto.country.CountryReadDto;

@Mapper
public interface CountryMapper {
        @Mapping(target = "auditingInfoDto", source = ".")
        CountryReadDto toDto(Country country);  // Entity to DTO

        Country toEntity(CountryCreateUpdateDto createUpdateDto);

        Country update(@MappingTarget Country country, CountryCreateUpdateDto countryCreateUpdateDto);
}