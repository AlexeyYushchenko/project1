package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Manufacturer;
import utlc.ru.project1.dto.manufacturer.ManufacturerCreateUpdateDto;
import utlc.ru.project1.dto.manufacturer.ManufacturerReadDto;

@Mapper
public interface ManufacturerMapper{
    ManufacturerReadDto toDto(Manufacturer manufacturer);  // Entity to DTO

    Manufacturer toEntity(ManufacturerCreateUpdateDto dto);  // DTO to Entity

    Manufacturer update(@MappingTarget Manufacturer manufacturer, ManufacturerCreateUpdateDto dto);
}
