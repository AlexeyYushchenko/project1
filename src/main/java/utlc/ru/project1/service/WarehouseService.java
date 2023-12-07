package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.CountryRepository;
import utlc.ru.project1.database.repository.WarehouseRepository;
import utlc.ru.project1.dto.warehouse.WarehouseCreateUpdateDto;
import utlc.ru.project1.dto.warehouse.WarehouseReadDto;
import utlc.ru.project1.mapper.WarehouseMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final CountryRepository countryRepository;
    private final WarehouseMapper warehouseMapper;

    public List<WarehouseReadDto> findAll() {
        return warehouseRepository.findAll().stream()
                .map(warehouseMapper::toDto)
                .toList();
    }

    public Optional<WarehouseReadDto> findById(Integer id) {
        return warehouseRepository.findById(id)
                .map(warehouseMapper::toDto);
    }

    @Transactional
    public WarehouseReadDto create(WarehouseCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(warehouseMapper::toEntity)
                .map(warehouseRepository::save)
                .map(warehouseMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<WarehouseReadDto> update(Integer id, WarehouseCreateUpdateDto dto) {
        return warehouseRepository.findById(id)
                .map(entity -> {
                    warehouseMapper.update(entity, dto); //all entity fields are updated except Country.
                    var country = Optional.ofNullable(dto.countryId())
                            .flatMap(countryRepository::findById)
                            .orElse(null);
                    entity.setCountry(country);
                    return entity;
                })
                .map(warehouseRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(warehouseMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return warehouseRepository.findById(id)
                .map(warehouse -> {
                    warehouseRepository.delete(warehouse);
                    warehouseRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
