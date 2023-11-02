package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.ClientStatus;
import utlc.ru.project1.dto.ClientStatusDto;

@Mapper
public interface ClientStatusMapper {
    ClientStatusDto toDto(ClientStatus client);  // Entity to DTO
    ClientStatus toEntity(ClientStatusDto clientDto);  // DTO to Entity

    ClientStatus update(@MappingTarget ClientStatus entity, ClientStatusDto dto);
}
