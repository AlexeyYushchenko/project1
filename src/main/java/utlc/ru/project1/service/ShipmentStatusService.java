package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.ShipmentStatusRepository;
import utlc.ru.project1.dto.shipmentstatus.ShipmentStatusCreateUpdateDto;
import utlc.ru.project1.dto.shipmentstatus.ShipmentStatusReadDto;
import utlc.ru.project1.mapper.ShipmentStatusMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShipmentStatusService {

    private final ShipmentStatusRepository shipmentStatusRepository;
    private final ShipmentStatusMapper shipmentStatusMapper;

    public List<ShipmentStatusReadDto> findAll() {
        return shipmentStatusRepository.findAll().stream()
                .map(shipmentStatusMapper::toDto)
                .toList();
    }

    public Optional<ShipmentStatusReadDto> findById(Integer id) {
        return shipmentStatusRepository.findById(id)
                .map(shipmentStatusMapper::toDto);
    }

    @Transactional
    public ShipmentStatusReadDto create(ShipmentStatusCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(shipmentStatusMapper::toEntity)
                .map(shipmentStatusRepository::save)
                .map(shipmentStatusMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<ShipmentStatusReadDto> update(Integer id, ShipmentStatusCreateUpdateDto createUpdateDto) {
        return shipmentStatusRepository.findById(id)
                .map(entity -> shipmentStatusMapper.update(entity, createUpdateDto))
                .map(shipmentStatusRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(shipmentStatusMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return shipmentStatusRepository.findById(id)
                .map(shipmentStatus -> {
                    shipmentStatusRepository.delete(shipmentStatus);
                    shipmentStatusRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
