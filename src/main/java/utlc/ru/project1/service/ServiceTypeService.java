package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.ServiceTypeRepository;
import utlc.ru.project1.dto.servicetype.ServiceTypeReadDto;
import utlc.ru.project1.dto.servicetype.ServiceTypeCreateUpdateDto;
import utlc.ru.project1.mapper.ServiceTypeMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceTypeMapper serviceTypeMapper;

    public List<ServiceTypeReadDto> findAll() {
        return serviceTypeRepository.findAll().stream()
                .map(serviceTypeMapper::toDto)
                .toList();
    }

    public Optional<ServiceTypeReadDto> findById(Integer id) {
        return serviceTypeRepository.findById(id)
                .map(serviceTypeMapper::toDto);
    }

    @Transactional
    public ServiceTypeReadDto create(ServiceTypeCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(serviceTypeMapper::toEntity)
                .map(serviceTypeRepository::save)
                .map(serviceTypeMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<ServiceTypeReadDto> update(Integer id, ServiceTypeCreateUpdateDto createUpdateDto) {
        return serviceTypeRepository.findById(id)
                .map(entity -> serviceTypeMapper.update(entity, createUpdateDto))
                .map(serviceTypeRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(serviceTypeMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return serviceTypeRepository.findById(id)
                .map(serviceType -> {
                    serviceTypeRepository.delete(serviceType);
                    serviceTypeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
