package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.entity.Client;
import utlc.ru.project1.database.repository.BusinessTypeRepository;
import utlc.ru.project1.database.repository.ClientStatusRepository;
import utlc.ru.project1.database.repository.ClientRepository;
import utlc.ru.project1.database.repository.IndustryTypeRepository;
import utlc.ru.project1.dto.client.ClientCreateUpdateDto;
import utlc.ru.project1.dto.client.ClientReadDto;
import utlc.ru.project1.mapper.ClientMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientStatusRepository clientStatusRepository;
    private final BusinessTypeRepository businessTypeRepository;
    private final IndustryTypeRepository industryTypeRepository;

    public List<ClientReadDto> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    public Optional<ClientReadDto> findById(Integer id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto);
    }

    @Transactional
    public ClientReadDto create(ClientCreateUpdateDto dto) {
        return Optional.of(dto)
                .map(clientMapper::toEntity)
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(clientRepository::save)
                .map(clientMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<ClientReadDto> update(Integer id, ClientCreateUpdateDto dto) {
        return clientRepository.findById(id)
                .map(entity -> clientMapper.update(entity, dto))
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(clientRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(clientMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    clientRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    private Client setUpOtherEntitiesToMainEntity(Client entity, ClientCreateUpdateDto dto) {
        var clientStatus = Optional.ofNullable(dto.clientStatusId())
                .flatMap(clientStatusRepository::findById)
                .orElse(null);
        entity.setClientStatus(clientStatus);
        var industryType = Optional.ofNullable(dto.industryTypeId())
                .flatMap(industryTypeRepository::findById)
                .orElse(null);
        entity.setIndustryType(industryType);
        var businessType = Optional.ofNullable(dto.businessTypeId())
                .flatMap(businessTypeRepository::findById)
                .orElse(null);
        entity.setBusinessType(businessType);

        return entity;
    }
}