package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.entity.Invoice;
import utlc.ru.project1.database.repository.*;
import utlc.ru.project1.dto.invoice.InvoiceCreateUpdateDto;
import utlc.ru.project1.dto.invoice.InvoiceReadDto;
import utlc.ru.project1.mapper.InvoiceMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvoiceService {

    private final InvoiceMapper invoiceMapper;
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final CurrencyRepository currencyRepository;
    private final ShipmentRepository shipmentRepository;
    private final InvoiceStatusRepository invoiceStatusRepository;

    public List<InvoiceReadDto> findAll() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toDto)
                .toList();
    }

    public Optional<InvoiceReadDto> findById(Long id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toDto);
    }

    @Transactional
    public InvoiceReadDto create(InvoiceCreateUpdateDto dto) {
        return Optional.of(dto)
                .map(invoiceMapper::toEntity)
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(invoiceRepository::save)
                .map(invoiceMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<InvoiceReadDto> update(Long id, InvoiceCreateUpdateDto dto) {
        return invoiceRepository.findById(id)
                .map(entity -> invoiceMapper.update(entity, dto))
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(invoiceRepository::saveAndFlush)
                .map(invoiceMapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    invoiceRepository.delete(invoice);
                    invoiceRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    private Invoice setUpOtherEntitiesToMainEntity(Invoice entity, InvoiceCreateUpdateDto dto) {
        var client = Optional.ofNullable(dto.clientId())
                .flatMap(clientRepository::findById)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        entity.setClient(client);

        var serviceType = Optional.ofNullable(dto.serviceTypeId())
                .flatMap(serviceTypeRepository::findById)
                .orElseThrow(() -> new RuntimeException("ServiceType not found"));
        entity.setServiceType(serviceType);

        var shipment = Optional.ofNullable(dto.shipmentId())
                .flatMap(shipmentRepository::findById)
                .orElse(null);
        entity.setShipment(shipment);

        var currency = Optional.ofNullable(dto.currencyId())
                .flatMap(currencyRepository::findById)
                .orElseThrow(() -> new RuntimeException("Currency not found"));
        entity.setCurrency(currency);

        var invoiceStatus = Optional.ofNullable(dto.invoiceStatusId())
                .flatMap(invoiceStatusRepository::findById)
                .orElseThrow(() -> new RuntimeException("Invoice Status not found"));
        entity.setInvoiceStatus(invoiceStatus);

        return entity;
    }

//      не лучше ли разбить метод и сделать его так?
//    private Invoice setUpOtherEntitiesToMainEntity(Invoice entity, InvoiceCreateUpdateDto dto) {
//        entity.setClient(findClientById(dto.getClientId()));
//        entity.setServiceType(findServiceTypeById(dto.getServiceTypeId()));
//        entity.setShipment(findShipmentById(dto.getShipmentId())); // This can return null, handled gracefully
//        entity.setCurrency(findCurrencyById(dto.getCurrencyId()));
//        entity.setInvoiceStatus(findInvoiceStatusById(dto.getInvoiceStatusId()));
//
//        return entity;
//    }
//
//    private Client findClientById(Long clientId) {
//        return clientRepository.findById(clientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Client not found for ID: " + clientId));
//    }
//
//    private ServiceType findServiceTypeById(Long serviceTypeId) {
//        return serviceTypeRepository.findById(serviceTypeId)
//                .orElseThrow(() -> new ResourceNotFoundException("ServiceType not found for ID: " + serviceTypeId));
//    }
//
//    private Shipment findShipmentById(Long shipmentId) {
//        return shipmentRepository.findById(shipmentId)
//                .orElse(null); // Consider if this should throw an exception or be handled differently
//    }
//
//    private Currency findCurrencyById(Long currencyId) {
//        return currencyRepository.findById(currencyId)
//                .orElseThrow(() -> new ResourceNotFoundException("Currency not found for ID: " + currencyId));
//    }
//
//    private InvoiceStatus findInvoiceStatusById(Long invoiceStatusId) {
//        return invoiceStatusRepository.findById(invoiceStatusId)
//                .orElseThrow(() -> new ResourceNotFoundException("Invoice Status not found for ID: " + invoiceStatusId));
//    }
}