package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.entity.Currency;
import utlc.ru.project1.database.repository.CurrencyRepository;
import utlc.ru.project1.dto.currency.CurrencyCreateUpdateDto;
import utlc.ru.project1.dto.currency.CurrencyReadDto;
import utlc.ru.project1.dto.industrytype.IndustryTypeCreateUpdateDto;
import utlc.ru.project1.dto.industrytype.IndustryTypeReadDto;
import utlc.ru.project1.mapper.CurrencyMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    public List<CurrencyReadDto> findAll() {
        return currencyRepository.findAll().stream()
                .map(currencyMapper::toDto)
                .toList();
    }

    public Optional<CurrencyReadDto> findById(Integer id) {
        return currencyRepository.findById(id)
                .map(currencyMapper::toDto);
    }

    @Transactional
    public CurrencyReadDto create(CurrencyCreateUpdateDto createDto) {
        return Optional.of(createDto)
                .map(currencyMapper::toEntity)
                .map(currencyRepository::save)
                .map(currencyMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<CurrencyReadDto> update(Integer id, CurrencyCreateUpdateDto dto) {
        return currencyRepository.findById(id)
                .map(entity -> currencyMapper.update(entity, dto))
                .map(currencyRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(currencyMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return currencyRepository.findById(id)
                .map(currency -> {
                    currencyRepository.delete(currency);
                    currencyRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
