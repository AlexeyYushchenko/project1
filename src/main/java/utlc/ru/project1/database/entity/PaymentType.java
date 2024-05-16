package utlc.ru.project1.database.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "payment_type")
public class PaymentType extends AuditingEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "payment_type_localization", joinColumns = @JoinColumn(name = "payment_type_id"))
    @MapKeyColumn(name = "language_code")
    @Column(name = "localized_name")
    private Map<String, String> nameLocales = new HashMap<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "payment_type_localization", joinColumns = @JoinColumn(name = "payment_type_id"))
    @MapKeyColumn(name = "language_code")
    @Column(name = "localized_description")
    private Map<String, String> descriptionLocales = new HashMap<>();
}