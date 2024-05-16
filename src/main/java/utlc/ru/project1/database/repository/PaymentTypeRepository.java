package utlc.ru.project1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utlc.ru.project1.database.entity.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
}