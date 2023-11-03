package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.ClientStatus;
import utlc.ru.project1.dto.ClientStatusReadDto;
import utlc.ru.project1.dto.ClientStatusUpdateDto;

@Mapper
public interface ClientStatusMapper {
    ClientStatusReadDto toDto(ClientStatus client);  // Entity to DTO
    ClientStatus toEntity(ClientStatusReadDto clientDto);  // DTO to Entity

    ClientStatus update(@MappingTarget ClientStatus entity, ClientStatusUpdateDto dto);
}
