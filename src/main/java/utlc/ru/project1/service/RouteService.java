package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.entity.Country;
import utlc.ru.project1.database.entity.Route;
import utlc.ru.project1.database.repository.*;
import utlc.ru.project1.dto.route.RouteCreateUpdateDto;
import utlc.ru.project1.dto.route.RouteReadDto;
import utlc.ru.project1.mapper.RouteMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final RouteStatusRepository routeStatusRepository;
    private final CountryRepository countryRepository;

    public List<RouteReadDto> findAll() {
        return routeRepository.findAll().stream()
                .map(routeMapper::toDto)
                .toList();
    }

    public Optional<RouteReadDto> findById(Long id) {
        return routeRepository.findById(id)
                .map(routeMapper::toDto);
    }

    @Transactional
    public RouteReadDto create(RouteCreateUpdateDto dto) {
        return Optional.of(dto)
                .map(routeMapper::toEntity)
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(routeRepository::save)
                .map(routeMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<RouteReadDto> update(Long id, RouteCreateUpdateDto dto) {
        return routeRepository.findById(id)
                .map(entity -> routeMapper.update(entity, dto))
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(routeRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(routeMapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return routeRepository.findById(id)
                .map(route -> {
                    routeRepository.delete(route);
                    routeRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    private Route setUpOtherEntitiesToMainEntity(Route entity, RouteCreateUpdateDto dto) {
        var routeStatus = Optional.ofNullable(dto.routeStatusId())
                .flatMap(routeStatusRepository::findById)
                .orElse(null);
        entity.setRouteStatus(routeStatus);
        Country countryOfDeparture = Optional.ofNullable(dto.countryOfDepartureId())
                .flatMap(countryRepository::findById)
                .orElse(null);
        entity.setCountryOfDeparture(countryOfDeparture);
        Country countryOfDestination = Optional.ofNullable(dto.countryOfDestinationId())
                .flatMap(countryRepository::findById)
                .orElse(null);
        entity.setCountryOfDestination(countryOfDestination);
        return entity;
    }
}