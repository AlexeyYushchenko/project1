package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.entity.Shipment;
import utlc.ru.project1.database.repository.*;
import utlc.ru.project1.dto.shipment.ShipmentCreateUpdateDto;
import utlc.ru.project1.dto.shipment.ShipmentReadDto;
import utlc.ru.project1.mapper.ShipmentMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentStatusRepository shipmentStatusRepository;
    private final ClientRepository clientRepository;
    private final PriorityRepository priorityRepository;
    private final RouteRepository routeRepository;
    private final PickUpPointRepository pickUpPointRepository;
    private final CountryRepository countryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public List<ShipmentReadDto> findAll() {
        return shipmentRepository.findAll().stream()
                .map(shipmentMapper::toDto)
                .toList();
    }

    public Optional<ShipmentReadDto> findById(Long id) {
        return shipmentRepository.findById(id)
                .map(shipmentMapper::toDto);
    }

    @Transactional
    public ShipmentReadDto create(ShipmentCreateUpdateDto dto) {
        return Optional.of(dto)
                .map(shipmentMapper::toEntity)
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(shipmentRepository::save)
                .map(shipmentMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<ShipmentReadDto> update(Long id, ShipmentCreateUpdateDto dto) {
        return shipmentRepository.findById(id)
                .map(entity -> shipmentMapper.update(entity, dto))
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(shipmentRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(shipmentMapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return shipmentRepository.findById(id)
                .map(shipment -> {
                    shipmentRepository.delete(shipment);
                    shipmentRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    private Shipment setUpOtherEntitiesToMainEntity(Shipment entity, ShipmentCreateUpdateDto dto) {
        var shipmentStatus = Optional.ofNullable(dto.shipmentStatusId())
                .flatMap(shipmentStatusRepository::findById)
                .orElse(null);
        entity.setShipmentStatus(shipmentStatus);
        var client = Optional.ofNullable(dto.clientId())
                .flatMap(clientRepository::findById)
                .orElse(null);
        entity.setClient(client);
        var priority = Optional.ofNullable(dto.priorityId())
                .flatMap(priorityRepository::findById)
                .orElse(null);
        entity.setPriority(priority);
        var route = Optional.ofNullable(dto.routeId())
                .flatMap(routeRepository::findById)
                .orElse(null);
        entity.setRoute(route);
        var pickUpPoint = Optional.ofNullable(dto.pickUpPointId())
                .flatMap(pickUpPointRepository::findById)
                .orElse(null);
        entity.setPickUpPoint(pickUpPoint);
        var countryOfDeparture = Optional.ofNullable(dto.countryOfDepartureId())
                .flatMap(countryRepository::findById)
                .orElse(null);
        entity.setCountryOfDeparture(countryOfDeparture);
        var countryOfDestination = Optional.ofNullable(dto.countryOfDestinationId())
                .flatMap(countryRepository::findById)
                .orElse(null);
        entity.setCountryOfDestination(countryOfDestination);
        var manufacturer = Optional.ofNullable(dto.manufacturerId())
                .flatMap(manufacturerRepository::findById)
                .orElse(null);
        entity.setManufacturer(manufacturer);

        return entity;
    }
}