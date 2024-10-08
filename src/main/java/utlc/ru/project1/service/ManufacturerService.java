package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.entity.Country;
import utlc.ru.project1.database.entity.Manufacturer;
import utlc.ru.project1.database.repository.CountryRepository;
import utlc.ru.project1.database.repository.ManufacturerRepository;
import utlc.ru.project1.dto.manufacturer.ManufacturerCreateUpdateDto;
import utlc.ru.project1.dto.manufacturer.ManufacturerReadDto;
import utlc.ru.project1.mapper.ManufacturerMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final CountryRepository countryRepository;
    private final ManufacturerMapper manufacturerMapper;

    public List<ManufacturerReadDto> findAll() {
        return manufacturerRepository.findAll().stream()
                .map(manufacturerMapper::toDto)
                .toList();
    }

    public Optional<ManufacturerReadDto> findById(Integer id) {
        return manufacturerRepository.findById(id)
                .map(manufacturerMapper::toDto);
    }

    @Transactional
    public ManufacturerReadDto create(ManufacturerCreateUpdateDto dto) {
        return Optional.of(dto)
                .map(manufacturerMapper::toEntity)
                .map(entity -> setUpCountryToManufacturer(entity, dto))
                .map(manufacturerRepository::save)
                .map(manufacturerMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<ManufacturerReadDto> update(Integer id, ManufacturerCreateUpdateDto dto) {
        return manufacturerRepository.findById(id)
                .map(entity -> manufacturerMapper.update(entity, dto))
                .map(entity -> setUpCountryToManufacturer(entity, dto))
                .map(manufacturerRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(manufacturerMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return manufacturerRepository.findById(id)
                .map(manufacturer -> {
                    manufacturerRepository.delete(manufacturer);
                    manufacturerRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    private Manufacturer setUpCountryToManufacturer(Manufacturer entity, ManufacturerCreateUpdateDto dto) {
        Country country = Optional.ofNullable(dto.countryId())
                .flatMap(countryRepository::findById)
                .orElse(null);
        entity.setCountry(country);
        return entity;
    }
}