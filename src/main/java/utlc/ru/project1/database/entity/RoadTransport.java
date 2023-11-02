package utlc.ru.project1.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "road_transport")
public class RoadTransport extends AuditingEntity<Long> {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Route route;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @Column(name = "truck_plate_number")
    private String truckPlateNumber;

    @Column(name = "trailer_plate_number")
    private String trailerPlateNumber;

    @Column(name = "cmr")
    private String cmr;

    @Column(name = "truck_type", nullable = false)
    private String truckType;

    @Column(name = "movement_plan")
    private String movementPlan;

    @Column(name = "comments")
    private String comments;
}
