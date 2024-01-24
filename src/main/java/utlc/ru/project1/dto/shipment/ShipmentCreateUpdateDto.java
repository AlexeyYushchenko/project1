package utlc.ru.project1.dto.shipment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import utlc.ru.project1.database.entity.*;
import java.time.LocalDateTime;
import java.util.List;

public record ShipmentCreateUpdateDto(

        @NotNull(message = "validation.client.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.client.name.pattern")
        @Size(min = 2, max = 50, message = "validation.client.name.size")


        @NotNull(message = "validation.shipment.shipmentStatus.required")
        Integer shipmentStatusId,

        @NotNull(message = "validation.shipment.client.required")
        Integer clientId,

        @NotNull(message = "validation.client.priority.required")
        Integer priorityId,

        Long routeId,

        String internalComment,

        String clientComment,

        String warehouseComment,

        LocalDateTime datePlaced,

        LocalDateTime dateChecked,

        LocalDateTime dateReadyDispatch,

        LocalDateTime dateReachedWarehouse,

        LocalDateTime dateLoading,

        LocalDateTime dateUnloading,

        @NotNull(message = "validation.client.deliveryType.required")
        String deliveryType,

        LocalDateTime dateConfirmedDispatch,

        Integer pickUpPointId,

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

        @NotNull(message = "validation.client.countryOfDeparture.required")
        Integer countryOfDepartureId,

        @NotNull(message = "validation.client.manufacturer.required")
        Integer manufacturerId,

        @NotNull(message = "validation.client.countryOfDestination.required")
        Integer countryOfDestinationId,

        List<ShipmentStatusHistory> statusHistories
) {
}
