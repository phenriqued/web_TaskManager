package phenriqued.com.github.web_TaskManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import phenriqued.com.github.web_TaskManager.entities.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {


}
