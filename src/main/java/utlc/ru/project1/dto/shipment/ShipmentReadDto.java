package utlc.ru.project1.dto.shipment;

import utlc.ru.project1.database.entity.ShipmentStatusHistory;
import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;
import utlc.ru.project1.dto.client.ClientReadDto;
import utlc.ru.project1.dto.country.CountryReadDto;
import utlc.ru.project1.dto.manufacturer.ManufacturerReadDto;
import utlc.ru.project1.dto.pickuppoint.PickUpPointReadDto;
import utlc.ru.project1.dto.priority.PriorityReadDto;
import utlc.ru.project1.dto.route.RouteReadDto;
import utlc.ru.project1.dto.shipmentstatus.ShipmentStatusReadDto;
import java.time.LocalDateTime;
import java.util.List;

public record ShipmentReadDto(
        Long id,
        ShipmentStatusReadDto shipmentStatus,
        ClientReadDto client,
        PriorityReadDto priority,
        RouteReadDto route,
        String internalComment,
        String clientComment,
        String warehouseComment,
        LocalDateTime datePlaced,
        LocalDateTime dateOrderProcessed,
        LocalDateTime dateReadyForDispatch,
        LocalDateTime dateReachedWarehouse,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        String deliveryType,
        LocalDateTime dateConfirmedDispatch,
        PickUpPointReadDto pickUpPoint,
        String destinationAddress,
        Integer clientPcs,
        Float clientVolumeM3,
        Float clientWeightKg,
        String shipmentType,
        String shipmentDescription,
        Integer warehousePcs,
        Float warehouseVolumeM3,
        Float warehouseWeightKg,
        String warehouseDiffComment,
        CountryReadDto countryOfDeparture,
        ManufacturerReadDto manufacturer,
        CountryReadDto countryOfDestination,
        List<ShipmentStatusHistory> statusHistories,
        AuditingInfoDto auditingInfoDto
) {
}
