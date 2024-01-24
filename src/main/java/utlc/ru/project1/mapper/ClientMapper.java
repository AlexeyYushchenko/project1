package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Client;
import utlc.ru.project1.dto.client.ClientCreateUpdateDto;
import utlc.ru.project1.dto.client.ClientReadDto;

@Mapper
public interface ClientMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    ClientReadDto toDto(Client client);

    Client toEntity(ClientCreateUpdateDto createUpdateDto);

    Client update(@MappingTarget Client entity, ClientCreateUpdateDto createUpdateDto);
}
