package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.ShipmentStatus;
import utlc.ru.project1.dto.shipmentstatus.ShipmentStatusReadDto;
import utlc.ru.project1.dto.shipmentstatus.ShipmentStatusUpdateDto;

@Mapper
public interface ShipmentStatusMapper {
    ShipmentStatusReadDto toDto(ShipmentStatus shipmentStatus);  // Entity to DTO
    ShipmentStatus toEntity(ShipmentStatusReadDto shipmentStatusReadDto);  // DTO to Entity
    ShipmentStatus update(@MappingTarget ShipmentStatus shipmentStatus, ShipmentStatusUpdateDto shipmentStatusUpdateDto);
}
