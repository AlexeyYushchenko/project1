package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.InvoiceStatusRepository;
import utlc.ru.project1.dto.invoicestatus.InvoiceStatusCreateUpdateDto;
import utlc.ru.project1.dto.invoicestatus.InvoiceStatusReadDto;
import utlc.ru.project1.mapper.InvoiceStatusMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvoiceStatusService {

    private final InvoiceStatusRepository invoiceStatusRepository;
    private final InvoiceStatusMapper invoiceStatusMapper;

    public List<InvoiceStatusReadDto> findAll() {
        return invoiceStatusRepository.findAll().stream()
                .map(invoiceStatusMapper::toDto)
                .toList();
    }

    public Optional<InvoiceStatusReadDto> findById(Integer id) {
        return invoiceStatusRepository.findById(id)
                .map(invoiceStatusMapper::toDto);
    }

    @Transactional
    public InvoiceStatusReadDto create(InvoiceStatusCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(invoiceStatusMapper::toEntity)
                .map(invoiceStatusRepository::save)
                .map(invoiceStatusMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<InvoiceStatusReadDto> update(Integer id, InvoiceStatusCreateUpdateDto createUpdateDto) {
        return invoiceStatusRepository.findById(id)
                .map(entity -> invoiceStatusMapper.update(entity, createUpdateDto))
                .map(invoiceStatusRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(invoiceStatusMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return invoiceStatusRepository.findById(id)
                .map(invoiceStatus -> {
                    invoiceStatusRepository.delete(invoiceStatus);
                    invoiceStatusRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
