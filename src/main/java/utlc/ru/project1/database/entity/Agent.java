package utlc.ru.project1.database.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agent")
public class Agent extends AuditingEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "commentary")
    private String commentary;
}
