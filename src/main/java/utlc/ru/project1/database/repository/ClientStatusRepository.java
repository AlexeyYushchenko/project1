package utlc.ru.project1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utlc.ru.project1.database.entity.ClientStatus;

public interface ClientStatusRepository extends JpaRepository<ClientStatus, Integer> {

}
