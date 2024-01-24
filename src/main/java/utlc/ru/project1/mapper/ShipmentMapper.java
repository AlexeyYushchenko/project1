package utlc.ru.project1.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Shipment;
import utlc.ru.project1.dto.shipment.ShipmentCreateUpdateDto;
import utlc.ru.project1.dto.shipment.ShipmentReadDto;

@Mapper
public interface ShipmentMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    ShipmentReadDto toDto(Shipment route);

    Shipment toEntity(ShipmentCreateUpdateDto createUpdateDto);

    Shipment update(@MappingTarget Shipment route, ShipmentCreateUpdateDto createUpdateDto);

}
