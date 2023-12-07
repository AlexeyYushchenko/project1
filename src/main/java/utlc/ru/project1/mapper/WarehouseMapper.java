package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Warehouse;
import utlc.ru.project1.dto.warehouse.WarehouseCreateUpdateDto;
import utlc.ru.project1.dto.warehouse.WarehouseReadDto;

@Mapper
public interface WarehouseMapper {
    WarehouseReadDto toDto(Warehouse warehouse);  // Entity to DTO

    Warehouse toEntity(WarehouseCreateUpdateDto dto);  // DTO to Entity

//    @RequiredArgsConstructor
//    class Repository{
//        private final CountryRepository countryRepository;
//        private Country getCountry(Integer countryId) {
//            return Optional.ofNullable(countryId)
//                    .flatMap(countryRepository::findById)
//                    .orElse(null);
//        }
//    }

    Warehouse update(@MappingTarget Warehouse warehouse, WarehouseCreateUpdateDto dto);
}