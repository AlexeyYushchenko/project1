package utlc.ru.project1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utlc.ru.project1.database.entity.Invoice;
import utlc.ru.project1.database.entity.InvoiceStatus;

public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus, Integer> {
}

