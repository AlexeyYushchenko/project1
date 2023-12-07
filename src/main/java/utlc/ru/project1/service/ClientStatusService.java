package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.ClientStatusRepository;
import utlc.ru.project1.dto.clientstatus.ClientStatusCreateUpdateDto;
import utlc.ru.project1.dto.clientstatus.ClientStatusReadDto;
import utlc.ru.project1.mapper.ClientStatusMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientStatusService {

    private final ClientStatusRepository clientStatusRepository;
    private final ClientStatusMapper clientStatusMapper;

    public List<ClientStatusReadDto> findAll() {
        return clientStatusRepository.findAll().stream()
                .map(clientStatusMapper::toDto)
                .toList();
    }

    public Optional<ClientStatusReadDto> findById(Integer id) {
        return clientStatusRepository.findById(id)
                .map(clientStatusMapper::toDto);
    }

    @Transactional
    public ClientStatusReadDto create(ClientStatusCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(clientStatusMapper::toEntity)
                .map(clientStatusRepository::save)
                .map(clientStatusMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<ClientStatusReadDto> update(Integer id, ClientStatusCreateUpdateDto createUpdateDto) {
        return clientStatusRepository.findById(id)
                .map(entity -> clientStatusMapper.update(entity, createUpdateDto))
                .map(clientStatusRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(clientStatusMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return clientStatusRepository.findById(id)
                .map(clientStatus -> {
                    clientStatusRepository.delete(clientStatus);
                    clientStatusRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
