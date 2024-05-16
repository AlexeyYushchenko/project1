package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.ServiceType;
import utlc.ru.project1.dto.servicetype.ServiceTypeCreateUpdateDto;
import utlc.ru.project1.dto.servicetype.ServiceTypeReadDto;

@Mapper
public interface ServiceTypeMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    ServiceTypeReadDto toDto(ServiceType serviceType);  // Entity to DTO

    ServiceType toEntity(ServiceTypeCreateUpdateDto createUpdateDto);  // DTO to Entity

    ServiceType update(@MappingTarget ServiceType serviceType, ServiceTypeCreateUpdateDto createUpdateDto);
}