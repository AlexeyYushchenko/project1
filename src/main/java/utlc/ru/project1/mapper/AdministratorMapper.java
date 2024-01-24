package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Administrator;
import utlc.ru.project1.dto.administrator.AdministratorCreateUpdateDto;
import utlc.ru.project1.dto.administrator.AdministratorReadDto;

@Mapper
public interface AdministratorMapper {
        @Mapping(target = "auditingInfoDto", source = ".")
        AdministratorReadDto toDto(Administrator entity);  // Entity to DTO

        Administrator toEntity(AdministratorCreateUpdateDto dto);  // DTO to Entity

        Administrator update(@MappingTarget Administrator administrator, AdministratorCreateUpdateDto dto);
}