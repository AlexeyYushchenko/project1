package utlc.ru.project1.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private ShipmentStatus shipmentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "internal_comment")
    private String internalComment;

    @Column(name = "client_comment")
    private String clientComment;

    @Column(name = "warehouse_comment")
    private String warehouseComment;

    @Column(name = "date_placed")
    private LocalDateTime datePlaced;

    @Column(name = "date_order_processed")
    private LocalDateTime dateOrderProcessed;

    @Column(name = "date_ready_for_dispatch")
    private LocalDateTime dateReadyForDispatch;

    @Column(name = "date_confirmed_dispatch")
    private LocalDateTime dateConfirmedDispatch;

    @Column(name = "date_reached_warehouse")
    private LocalDateTime dateReachedWarehouse;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    //Delivery Type and Address
    @Column(name = "delivery_type", nullable = false)
    private String deliveryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pick_up_point_id")
    private PickUpPoint pickUpPoint;

    @Column(name = "destination_address")
    private String destinationAddress;

    //client-provided information
    @Column(name = "client_pcs")
    private Integer clientPcs;

    @Column(name = "client_volume_m3")
    private Float clientVolumeM3;

    @Column(name = "client_weight_kg")
    private Float clientWeightKg;

    @Column(name = "shipment_type", nullable = false)
    private String shipmentType;

    @Column(name = "shipment_description")
    private String shipmentDescription;

    //Verified warehouse information
    @Column(name = "warehouse_pcs")
    private Integer warehousePcs;

    @Column(name = "warehouse_volume_m3")
    private Float warehouseVolumeM3;

    @Column(name = "warehouse_weight_kg")
    private Float warehouseWeightKg;

    @Column(name = "warehouse_diff_comment")
    private String warehouseDiffComment;

    //Origin and Destination
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_of_departure")
    private Country countryOfDeparture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_of_destination")
    private Country countryOfDestination;

    //todo add history of statuses with dates and authors
//    @ElementCollection
//    @CollectionTable(name = "shipment_status_history",
//            joinColumns = @JoinColumn(name = "shipment_id"))
//    @MapKeyColumn(name = "language_code")
//    @Column(name = "localized_name")
//    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<ShipmentStatusHistory> statusHistories;
}
