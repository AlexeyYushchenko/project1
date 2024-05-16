package utlc.ru.project1.dto.payment;

import utlc.ru.project1.dto.auditinginfo.AuditingInfoDto;
import utlc.ru.project1.dto.client.ClientReadDto;
import utlc.ru.project1.dto.currency.CurrencyReadDto;
import utlc.ru.project1.dto.paymenttype.PaymentTypeReadDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentReadDto(
        Long id,
        ClientReadDto client,
        PaymentTypeReadDto paymentType,
        BigDecimal amount,
        CurrencyReadDto currency,
        LocalDate paymentDate,
        BigDecimal paymentProcessingFees,
        BigDecimal totalAmount,
        String commentary,
        AuditingInfoDto auditingInfoDto
) {
}