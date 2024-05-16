package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.entity.Payment;
import utlc.ru.project1.database.entity.Shipment;
import utlc.ru.project1.database.repository.ClientRepository;
import utlc.ru.project1.database.repository.CurrencyRepository;
import utlc.ru.project1.database.repository.PaymentRepository;
import utlc.ru.project1.database.repository.PaymentTypeRepository;
import utlc.ru.project1.dto.payment.PaymentCreateUpdateDto;
import utlc.ru.project1.dto.payment.PaymentReadDto;
import utlc.ru.project1.dto.shipment.ShipmentCreateUpdateDto;
import utlc.ru.project1.mapper.PaymentMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final ClientRepository clientRepository;
    private final CurrencyRepository currencyRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    public List<PaymentReadDto> findAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    public Optional<PaymentReadDto> findById(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDto);
    }

    @Transactional
    public PaymentReadDto create(PaymentCreateUpdateDto dto) {
        return Optional.of(dto)
                .map(paymentMapper::toEntity)
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(paymentRepository::save)
                .map(paymentMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<PaymentReadDto> update(Long id, PaymentCreateUpdateDto dto) {
        return paymentRepository.findById(id)
                .map(entity -> {
                    paymentMapper.update(entity, dto);
                    setUpOtherEntitiesToMainEntity(entity, dto);
                    calculateAndSetTotalAmount(entity, dto);
                    return paymentRepository.saveAndFlush(entity);
                })
                .map(paymentMapper::toDto);
    }

    private void calculateAndSetTotalAmount(Payment entity, PaymentCreateUpdateDto dto) {
        // Ensure amount and fees are not null, use BigDecimal.ZERO as default for fees if null
        BigDecimal amount = Optional.ofNullable(dto.amount())
                .orElse(BigDecimal.ZERO).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal processingFees = Optional.ofNullable(dto.paymentProcessingFees())
                .orElse(BigDecimal.ZERO).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        entity.setTotalAmount(amount.subtract(processingFees));
    }

    @Transactional
    public boolean delete(Long id) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    paymentRepository.delete(payment);
                    paymentRepository.flush();
                    return true;
                })
                .orElse(false);
    }


    private Payment setUpOtherEntitiesToMainEntity(Payment entity, PaymentCreateUpdateDto dto) {
        var client = Optional.ofNullable(dto.clientId())
                .flatMap(clientRepository::findById)
                .orElse(null);
        entity.setClient(client);

        var currency = Optional.ofNullable(dto.currencyId())
                .flatMap(currencyRepository::findById)
                .orElse(null);
        entity.setCurrency(currency);

        var paymentType = Optional.ofNullable(dto.paymentTypeId())
                .flatMap(paymentTypeRepository::findById)
                .orElse(null);
        entity.setPaymentType(paymentType);

        return entity;
    }
}