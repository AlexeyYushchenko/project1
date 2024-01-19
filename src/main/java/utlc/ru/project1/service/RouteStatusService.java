package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.RouteStatusRepository;
import utlc.ru.project1.dto.routestatus.RouteStatusCreateUpdateDto;
import utlc.ru.project1.dto.routestatus.RouteStatusReadDto;
import utlc.ru.project1.mapper.RouteStatusMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RouteStatusService {

    private final RouteStatusRepository routeStatusRepository;
    private final RouteStatusMapper routeStatusMapper;

    public List<RouteStatusReadDto> findAll() {
        return routeStatusRepository.findAll().stream()
                .map(routeStatusMapper::toDto)
                .toList();
    }

    public Optional<RouteStatusReadDto> findById(Integer id) {
        return routeStatusRepository.findById(id)
                .map(routeStatusMapper::toDto);
    }

    @Transactional
    public RouteStatusReadDto create(RouteStatusCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(routeStatusMapper::toEntity)
                .map(routeStatusRepository::save)
                .map(routeStatusMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<RouteStatusReadDto> update(Integer id, RouteStatusCreateUpdateDto createUpdateDto) {
        return routeStatusRepository.findById(id)
                .map(entity -> routeStatusMapper.update(entity, createUpdateDto))
                .map(routeStatusRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(routeStatusMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return routeStatusRepository.findById(id)
                .map(routeStatus -> {
                    routeStatusRepository.delete(routeStatus);
                    routeStatusRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
