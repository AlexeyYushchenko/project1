package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Priority;
import utlc.ru.project1.dto.priority.PriorityCreateUpdateDto;
import utlc.ru.project1.dto.priority.PriorityReadDto;

@Mapper
public interface PriorityMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    PriorityReadDto toDto(Priority priority);  // Entity to DTO

    Priority toEntity(PriorityCreateUpdateDto createUpdateDto);  // DTO to Entity

    Priority update(@MappingTarget Priority priority, PriorityCreateUpdateDto createUpdateDto);

}
