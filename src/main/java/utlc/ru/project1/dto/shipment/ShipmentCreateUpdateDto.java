package utlc.ru.project1.dto.shipment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import utlc.ru.project1.database.entity.*;
import java.time.LocalDateTime;
import java.util.List;

public record ShipmentCreateUpdateDto(

        @NotNull(message = "validation.shipment.shipmentStatus.required")
        Integer shipmentStatusId,

        @NotNull(message = "validation.shipment.client.required")
        Integer clientId,

        @NotNull(message = "validation.shipment.priority.required")
        Integer priorityId,

        Long routeId,

        String internalComment,

        String clientComment,

        String warehouseComment,

        LocalDateTime datePlaced,

        LocalDateTime dateOrderProcessed,

        LocalDateTime dateReadyForDispatch,

        LocalDateTime dateReachedWarehouse,

        LocalDateTime departureDate,

        LocalDateTime arrivalDate,

        @NotNull(message = "validation.shipment.deliveryType.required")
        @Size(max = 20, message = "validation.shipment.deliveryType.size")
        String deliveryType,

        LocalDateTime dateConfirmedDispatch,

        Integer pickUpPointId,

        @Size(max = 255, message = "validation.shipment.destinationAddress.size")
        String destinationAddress,

        Integer clientPcs,

        Float clientVolumeM3,

        Float clientWeightKg,

        @NotNull(message = "validation.shipment.shipmentType.required")
        @Size(max = 50, message = "validation.shipment.shipmentType.size")
        String shipmentType,

        String shipmentDescription,

        Integer warehousePcs,

        Float warehouseVolumeM3,

        Float warehouseWeightKg,

        String warehouseDiffComment,

        @NotNull(message = "validation.shipment.countryOfDeparture.required")
        Integer countryOfDepartureId,

        @NotNull(message = "validation.shipment.manufacturer.required")
        Integer manufacturerId,

        @NotNull(message = "validation.shipment.countryOfDestination.required")
        Integer countryOfDestinationId,

        List<ShipmentStatusHistory> statusHistories
) {
}
