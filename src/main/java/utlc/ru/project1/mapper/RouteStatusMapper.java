package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.RouteStatus;
import utlc.ru.project1.dto.routestatus.RouteStatusCreateUpdateDto;
import utlc.ru.project1.dto.routestatus.RouteStatusReadDto;

@Mapper
public interface RouteStatusMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    RouteStatusReadDto toDto(RouteStatus entity);  // Entity to DTO
    RouteStatus toEntity(RouteStatusCreateUpdateDto dto);  // DTO to Entity
    RouteStatus update(@MappingTarget RouteStatus entity, RouteStatusCreateUpdateDto dto);
}