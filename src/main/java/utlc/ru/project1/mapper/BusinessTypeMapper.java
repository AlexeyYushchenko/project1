package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.BusinessType;
import utlc.ru.project1.dto.businesstype.BusinessTypeReadDto;
import utlc.ru.project1.dto.businesstype.BusinessTypeCreateUpdateDto;

@Mapper
public interface BusinessTypeMapper {

    BusinessTypeReadDto toDto(BusinessType businessType);  // Entity to DTO

    BusinessType toEntity(BusinessTypeCreateUpdateDto createUpdateDto);  // DTO to Entity

    BusinessType update(@MappingTarget BusinessType businessType, BusinessTypeCreateUpdateDto createUpdateDto);
}
