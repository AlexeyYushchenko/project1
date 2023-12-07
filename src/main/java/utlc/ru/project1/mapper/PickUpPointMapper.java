package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.PickUpPoint;
import utlc.ru.project1.dto.pickuppoint.PickUpPointCreateUpdateDto;
import utlc.ru.project1.dto.pickuppoint.PickUpPointReadDto;

@Mapper
public interface PickUpPointMapper {
    PickUpPointReadDto toDto(PickUpPoint entity);  // Entity to DTO

    PickUpPoint toEntity(PickUpPointCreateUpdateDto dto);  // DTO to Entity

    PickUpPoint update(@MappingTarget PickUpPoint entity, PickUpPointCreateUpdateDto dto);
}
