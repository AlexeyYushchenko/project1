package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Route;
import utlc.ru.project1.dto.route.RouteCreateUpdateDto;
import utlc.ru.project1.dto.route.RouteReadDto;

@Mapper
public interface RouteMapper {
    RouteReadDto toDto(Route route);  // Entity to DTO

    Route toEntity(RouteCreateUpdateDto createUpdateDto);  // DTO to Entity

    Route update(@MappingTarget Route route, RouteCreateUpdateDto createUpdateDto);

}
