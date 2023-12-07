package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.PriorityRepository;
import utlc.ru.project1.dto.priority.PriorityReadDto;
import utlc.ru.project1.dto.priority.PriorityCreateUpdateDto;
import utlc.ru.project1.mapper.PriorityMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PriorityService {

    private final PriorityRepository priorityRepository;
    private final PriorityMapper priorityMapper;

    public List<PriorityReadDto> findAll() {
        return priorityRepository.findAll().stream()
                .map(priorityMapper::toDto)
                .toList();
    }

    public Optional<PriorityReadDto> findById(Integer id) {
        return priorityRepository.findById(id)
                .map(priorityMapper::toDto);
    }

    @Transactional
    public PriorityReadDto create(PriorityCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(priorityMapper::toEntity)
                .map(priorityRepository::save)
                .map(priorityMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<PriorityReadDto> update(Integer id, PriorityCreateUpdateDto createUpdateDto) {
        return priorityRepository.findById(id)
                .map(entity -> priorityMapper.update(entity, createUpdateDto))
                .map(priorityRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(priorityMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return priorityRepository.findById(id)
                .map(priority -> {
                    priorityRepository.delete(priority);
                    priorityRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
