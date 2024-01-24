package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Warehouse;
import utlc.ru.project1.dto.warehouse.WarehouseCreateUpdateDto;
import utlc.ru.project1.dto.warehouse.WarehouseReadDto;

@Mapper
public interface WarehouseMapper {

    @Mapping(target = "auditingInfoDto", source = ".")
    WarehouseReadDto toDto(Warehouse warehouse);  // Entity to DTO

    Warehouse toEntity(WarehouseCreateUpdateDto dto);  // DTO to Entity

    Warehouse update(@MappingTarget Warehouse warehouse, WarehouseCreateUpdateDto dto);
}