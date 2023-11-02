package utlc.ru.project1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utlc.ru.project1.database.entity.BusinessType;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer> {
}
