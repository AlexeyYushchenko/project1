package utlc.ru.project1.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(name = "transport_type")
    private String transportType;

    @Column(name = "is_international", nullable = false)
    private Boolean isInternational;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_of_departure")
    private Country countryOfDeparture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_of_destination")
    private Country countryOfDestination;

    @Column(name = "customs_post")
    private String customsPost;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "status")
    private String status;
}