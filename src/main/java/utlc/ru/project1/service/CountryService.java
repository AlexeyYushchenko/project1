package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.CountryRepository;
import utlc.ru.project1.dto.country.CountryReadDto;
import utlc.ru.project1.dto.country.CountryCreateUpdateDto;
import utlc.ru.project1.mapper.CountryMapper;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public List<CountryReadDto> findAll() {
        return countryRepository.findAll().stream()
                .map(countryMapper::toDto)
                .toList();
    }

    public Optional<CountryReadDto> findById(Integer id) {
        return countryRepository.findById(id)
                .map(countryMapper::toDto);
    }

    @Transactional
    public CountryReadDto create(CountryCreateUpdateDto createUpdateDto) {
        return Optional.of(createUpdateDto)
                .map(countryMapper::toEntity)
                .map(countryRepository::save)
                .map(countryMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<CountryReadDto> update(Integer id, CountryCreateUpdateDto createUpdateDto) {
        return countryRepository.findById(id)
                .map(entity -> countryMapper.update(entity, createUpdateDto))
                .map(countryRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(countryMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return countryRepository.findById(id)
                .map(country -> {
                    countryRepository.delete(country);
                    countryRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
