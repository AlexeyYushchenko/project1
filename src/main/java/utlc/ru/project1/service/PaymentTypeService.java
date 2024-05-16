package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.PaymentTypeRepository;
import utlc.ru.project1.dto.paymenttype.PaymentTypeCreateUpdateDto;
import utlc.ru.project1.dto.paymenttype.PaymentTypeReadDto;
import utlc.ru.project1.mapper.PaymentTypeMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;

    public List<PaymentTypeReadDto> findAll() {
        return paymentTypeRepository.findAll().stream()
                .map(paymentTypeMapper::toDto)
                .toList();
    }

    public Optional<PaymentTypeReadDto> findById(Integer id) {
        return paymentTypeRepository.findById(id)
                .map(paymentTypeMapper::toDto);
    }

    @Transactional
    public PaymentTypeReadDto create(PaymentTypeCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(paymentTypeMapper::toEntity)
                .map(paymentTypeRepository::save)
                .map(paymentTypeMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<PaymentTypeReadDto> update(Integer id, PaymentTypeCreateUpdateDto createUpdateDto) {
        return paymentTypeRepository.findById(id)
                .map(entity -> paymentTypeMapper.update(entity, createUpdateDto))
                .map(paymentTypeRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(paymentTypeMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return paymentTypeRepository.findById(id)
                .map(paymentType -> {
                    paymentTypeRepository.delete(paymentType);
                    paymentTypeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
