package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.IndustryTypeRepository;
import utlc.ru.project1.dto.industrytype.IndustryTypeReadDto;
import utlc.ru.project1.dto.industrytype.IndustryTypeCreateUpdateDto;
import utlc.ru.project1.mapper.IndustryTypeMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IndustryTypeService {

    private final IndustryTypeRepository industryTypeRepository;
    private final IndustryTypeMapper industryTypeMapper;

    public List<IndustryTypeReadDto> findAll() {
        return industryTypeRepository.findAll().stream()
                .map(industryTypeMapper::toDto)
                .toList();
    }

    public Optional<IndustryTypeReadDto> findById(Integer id) {
        return industryTypeRepository.findById(id)
                .map(industryTypeMapper::toDto);
    }

    @Transactional
    public IndustryTypeReadDto create(IndustryTypeCreateUpdateDto createDto) {
        return Optional.of(createDto)
                .map(industryTypeMapper::toEntity)
                .map(industryTypeRepository::save)
                .map(industryTypeMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<IndustryTypeReadDto> update(Integer id, IndustryTypeCreateUpdateDto dto) {
        return industryTypeRepository.findById(id)
                .map(entity -> industryTypeMapper.update(entity, dto))
                .map(industryTypeRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(industryTypeMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return industryTypeRepository.findById(id)
                .map(industryType -> {
                    industryTypeRepository.delete(industryType);
                    industryTypeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}