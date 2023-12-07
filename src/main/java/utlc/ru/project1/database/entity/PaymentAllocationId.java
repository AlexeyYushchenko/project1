package utlc.ru.project1.database.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PaymentAllocationId implements Serializable {
    private Long paymentId;
    private Long invoiceId;
}