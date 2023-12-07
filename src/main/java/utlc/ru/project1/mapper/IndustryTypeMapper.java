package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.IndustryType;
import utlc.ru.project1.dto.industrytype.IndustryTypeCreateUpdateDto;
import utlc.ru.project1.dto.industrytype.IndustryTypeReadDto;

@Mapper
public interface IndustryTypeMapper {
    IndustryTypeReadDto toDto(IndustryType industryType);  // Entity to DTO

    IndustryType toEntity(IndustryTypeCreateUpdateDto industryTypeCreateUpdateDto);  // DTO to Entity

    IndustryType update(@MappingTarget IndustryType industryType, IndustryTypeCreateUpdateDto industryTypeCreateUpdateDto);
}
