package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.AgentRepository;
import utlc.ru.project1.dto.agent.AgentCreateUpdateDto;
import utlc.ru.project1.dto.agent.AgentReadDto;
import utlc.ru.project1.mapper.AgentMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgentService {

    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;

    public List<AgentReadDto> findAll() {
        return agentRepository.findAll().stream()
                .map(agentMapper::toDto)
                .toList();
    }

    public Optional<AgentReadDto> findById(Integer id) {
        return agentRepository.findById(id)
                .map(agentMapper::toDto);
    }

    @Transactional
    public AgentReadDto create(AgentCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(agentMapper::toEntity)
                .map(agentRepository::save)
                .map(agentMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<AgentReadDto> update(Integer id, AgentCreateUpdateDto dto) {
        return agentRepository.findById(id)
                .map(entity -> agentMapper.update(entity, dto))
                .map(agentRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(agentMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return agentRepository.findById(id)
                .map(agent -> {
                    agentRepository.delete(agent);
                    agentRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}

