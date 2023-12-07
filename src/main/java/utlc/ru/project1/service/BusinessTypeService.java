package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.BusinessTypeRepository;
import utlc.ru.project1.dto.businesstype.BusinessTypeReadDto;
import utlc.ru.project1.dto.businesstype.BusinessTypeCreateUpdateDto;
import utlc.ru.project1.mapper.BusinessTypeMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessTypeService {

    private final BusinessTypeRepository businessTypeRepository;
    private final BusinessTypeMapper businessTypeMapper;

    public List<BusinessTypeReadDto> findAll() {
        return businessTypeRepository.findAll().stream()
                .map(businessTypeMapper::toDto)
                .toList();
    }

    public Optional<BusinessTypeReadDto> findById(Integer id) {
        return businessTypeRepository.findById(id)
                .map(businessTypeMapper::toDto);
    }

    @Transactional
    public BusinessTypeReadDto create(BusinessTypeCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(businessTypeMapper::toEntity)
                .map(businessTypeRepository::save)
                .map(businessTypeMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<BusinessTypeReadDto> update(Integer id, BusinessTypeCreateUpdateDto createUpdateDto) {
        return businessTypeRepository.findById(id)
                .map(entity -> businessTypeMapper.update(entity, createUpdateDto))
                .map(businessTypeRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(businessTypeMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return businessTypeRepository.findById(id)
                .map(businessType -> {
                    businessTypeRepository.delete(businessType);
                    businessTypeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
