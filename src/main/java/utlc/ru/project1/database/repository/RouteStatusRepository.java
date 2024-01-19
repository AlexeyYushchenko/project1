package utlc.ru.project1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utlc.ru.project1.database.entity.RouteStatus;

public interface RouteStatusRepository extends JpaRepository<RouteStatus, Integer> {
}

