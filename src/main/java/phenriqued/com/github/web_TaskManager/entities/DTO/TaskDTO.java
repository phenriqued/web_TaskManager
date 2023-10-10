package phenriqued.com.github.web_TaskManager.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import phenriqued.com.github.web_TaskManager.entities.TaskEntity;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TaskDTO{
    private Long id;
    private String textTask;
    private boolean checkTask;
    private LocalDate taskValidity;

    public TaskDTO(TaskEntity entity){
        BeanUtils.copyProperties(entity, this);
    }
}
