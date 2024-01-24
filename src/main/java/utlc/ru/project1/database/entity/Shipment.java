package utlc.ru.project1.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
    private ShipmentStatus status;

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

    @Column(name = "date_checked")
    private LocalDateTime dateChecked;

    @Column(name = "date_ready_dispatch")
    private LocalDateTime dateReadyDispatch;

    @Column(name = "date_reached_warehouse")
    private LocalDateTime dateReachedWarehouse;

    @Column(name = "date_loading")
    private LocalDateTime dateLoading;

    @Column(name = "date_unloading")
    private LocalDateTime dateUnloading;

    //Delivery Type and Address
    @Column(name = "delivery_type", nullable = false)
    private String deliveryType;

    @Column(name = "date_confirmed_dispatch", nullable = false)
    private LocalDateTime dateConfirmedDispatch;

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

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShipmentStatusHistory> statusHistories;
}
