package utlc.ru.project1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utlc.ru.project1.database.entity.RoadTransport;

public interface RoadTransportRepository extends JpaRepository<RoadTransport, Long> {
    // Custom queries can be added here if needed
}
