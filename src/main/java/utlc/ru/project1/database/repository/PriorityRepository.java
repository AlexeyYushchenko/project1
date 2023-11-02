package utlc.ru.project1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utlc.ru.project1.database.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {

}
