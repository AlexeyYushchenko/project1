package utlc.ru.project1.dto.invoice;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;
import utlc.ru.project1.dto.client.ClientReadDto;
import utlc.ru.project1.dto.currency.CurrencyReadDto;
import utlc.ru.project1.dto.invoicestatus.InvoiceStatusReadDto;
import utlc.ru.project1.dto.servicetype.ServiceTypeReadDto;
import utlc.ru.project1.dto.shipment.ShipmentReadDto;
import java.math.BigDecimal;
import java.time.LocalDate;

public record InvoiceReadDto(
        Long id,
        ClientReadDto client,
        ServiceTypeReadDto serviceType,
        BigDecimal totalAmount,
        CurrencyReadDto currency,
        LocalDate issueDate,
        LocalDate dueDate,
        String commentary,
        ShipmentReadDto shipment,
        InvoiceStatusReadDto invoiceStatus,
        AuditingInfoDto auditingInfoDto
) {
}

