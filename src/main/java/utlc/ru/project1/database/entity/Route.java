package utlc.ru.project1.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "route")
public class Route extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_number", nullable = false, unique = true, length = 100)
    private String identificationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private RouteStatus routeStatus;

    @Column(name = "transport_type")
    private String transportType;

    //flag for international/domestic routes
    @Column(name = "is_international", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isInternational;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_of_departure_id")
    private Country countryOfDeparture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_of_destination_id")
    private Country countryOfDestination;

    @Column(name = "customs_post")
    private String customsPost;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

//    pseudocode
//    public void updateOrderStatuses(int transportationUnitId, String newStatus) {
//        List<Integer> orderIds = getOrderIdsLinkedToTransportationUnit(transportationUnitId);
//        for (Integer orderId : orderIds) {
//            updateOrderStatus(orderId, newStatus);
//        }
//    }
}