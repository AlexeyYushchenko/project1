package utlc.ru.project1.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_allocation")
public class PaymentAllocation {

    @EmbeddedId
    private PaymentAllocationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("paymentId")
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("invoiceId")
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column(name = "allocated_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal allocatedAmount;
}
