package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.entity.Country;
import utlc.ru.project1.database.entity.PickUpPoint;
import utlc.ru.project1.database.repository.CountryRepository;
import utlc.ru.project1.database.repository.PickUpPointRepository;
import utlc.ru.project1.dto.pickuppoint.PickUpPointCreateUpdateDto;
import utlc.ru.project1.dto.pickuppoint.PickUpPointReadDto;
import utlc.ru.project1.mapper.PickUpPointMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PickUpPointService {

    private final PickUpPointRepository pickUpPointRepository;
    private final CountryRepository countryRepository;
    private final PickUpPointMapper pickUpPointMapper;

    public List<PickUpPointReadDto> findAll() {
        return pickUpPointRepository.findAll().stream()
                .map(pickUpPointMapper::toDto)
                .toList();
    }

    public Optional<PickUpPointReadDto> findById(Integer id) {
        return pickUpPointRepository.findById(id)
                .map(pickUpPointMapper::toDto);
    }

    @Transactional
    public PickUpPointReadDto create(PickUpPointCreateUpdateDto dto) {
        return Optional.of(dto)
                .map(pickUpPointMapper::toEntity)
                .map(entity -> setUpCountryToPickUpPoint(entity, dto))
                .map(pickUpPointRepository::save)
                .map(pickUpPointMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<PickUpPointReadDto> update(Integer id, PickUpPointCreateUpdateDto dto) {
        return pickUpPointRepository.findById(id)
                .map(entity -> pickUpPointMapper.update(entity, dto))
                .map(entity -> setUpCountryToPickUpPoint(entity, dto))
                .map(pickUpPointRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(pickUpPointMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return pickUpPointRepository.findById(id)
                .map(pickUpPoint -> {
                    pickUpPointRepository.delete(pickUpPoint);
                    pickUpPointRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    private PickUpPoint setUpCountryToPickUpPoint(PickUpPoint entity, PickUpPointCreateUpdateDto dto) {
        Country country = Optional.ofNullable(dto.countryId())
                .flatMap(countryRepository::findById)
                .orElse(null);
        entity.setCountry(country);
        return entity;
    }
}