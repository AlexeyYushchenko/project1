package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Route;
import utlc.ru.project1.dto.route.RouteCreateUpdateDto;
import utlc.ru.project1.dto.route.RouteReadDto;

@Mapper
public interface RouteMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    RouteReadDto toDto(Route route);

    Route toEntity(RouteCreateUpdateDto createUpdateDto);

    Route update(@MappingTarget Route route, RouteCreateUpdateDto createUpdateDto);

}
