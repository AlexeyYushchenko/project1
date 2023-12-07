package utlc.ru.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utlc.ru.project1.database.repository.AdministratorRepository;
import utlc.ru.project1.dto.administrator.AdministratorCreateDto;
import utlc.ru.project1.dto.administrator.AdministratorReadDto;
import utlc.ru.project1.dto.administrator.AdministratorUpdateDto;
import utlc.ru.project1.mapper.AdministratorMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdministratorService implements UserDetailsService {

    private final AdministratorRepository administratorRepository;
    private final AdministratorMapper administratorMapper;

    public List<AdministratorReadDto> findAll() {
        return administratorRepository.findAll().stream()
                .map(administratorMapper::toDto)
                .toList();
    }

    public Optional<AdministratorReadDto> findById(Integer id) {
        return administratorRepository.findById(id)
                .map(administratorMapper::toDto);
    }

    @Transactional
    public AdministratorReadDto create(AdministratorCreateDto administratorCreateDto) {
        return Optional.of(administratorCreateDto)
                .map(administratorMapper::toEntity)
                .map(administratorRepository::save)
                .map(administratorMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<AdministratorReadDto> update(Integer id, AdministratorUpdateDto dto) {
        return administratorRepository.findById(id)
                .map(entity -> administratorMapper.update(entity, dto))
                .map(administratorRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(administratorMapper::toDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return administratorRepository.findById(id)
                .map(administrator -> {
                    administratorRepository.delete(administrator);
                    administratorRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return administratorRepository.findByUsername(username)
                .map(admin -> new User(
                        admin.getUsername(),
                        admin.getPassword(),
                        Collections.singleton(admin.getRole())
                        ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve administrator: " + username));
    }
}
