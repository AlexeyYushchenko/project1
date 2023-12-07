package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.ClientStatus;
import utlc.ru.project1.dto.clientstatus.ClientStatusCreateUpdateDto;
import utlc.ru.project1.dto.clientstatus.ClientStatusReadDto;

@Mapper
public interface ClientStatusMapper {
    ClientStatusReadDto toDto(ClientStatus client);  // Entity to DTO
    ClientStatus toEntity(ClientStatusCreateUpdateDto createUpdateDto);  // DTO to Entity
    ClientStatus update(@MappingTarget ClientStatus entity, ClientStatusCreateUpdateDto createUpdateDto);
}