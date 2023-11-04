package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Priority;
import utlc.ru.project1.dto.priority.PriorityReadDto;
import utlc.ru.project1.dto.priority.PriorityUpdateDto;

@Mapper
public interface PriorityMapper {
    PriorityReadDto toDto(Priority priority);  // Entity to DTO

    Priority toEntity(PriorityReadDto priorityReadDto);  // DTO to Entity

    Priority update(@MappingTarget Priority priority, PriorityUpdateDto priorityUpdateDto);

}
