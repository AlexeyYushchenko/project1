package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Administrator;
import utlc.ru.project1.dto.administrator.AdministratorCreateDto;
import utlc.ru.project1.dto.administrator.AdministratorReadDto;
import utlc.ru.project1.dto.administrator.AdministratorUpdateDto;

@Mapper
public interface AdministratorMapper {
        AdministratorReadDto toDto(Administrator entity);  // Entity to DTO

        Administrator toEntity(AdministratorCreateDto dto);  // DTO to Entity

        Administrator update(@MappingTarget Administrator administrator, AdministratorUpdateDto dto);
}