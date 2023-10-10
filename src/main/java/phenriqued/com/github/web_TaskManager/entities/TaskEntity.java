package phenriqued.com.github.web_TaskManager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import phenriqued.com.github.web_TaskManager.entities.DTO.TaskDTO;
import phenriqued.com.github.web_TaskManager.entities.DTO.TaskRegistrationDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


@Entity
@Table(name = "tb_task")
@NoArgsConstructor
@Getter
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String textTask;
    @Setter private boolean checkTask;
    private LocalDate taskValidity;

    public TaskEntity(TaskRegistrationDTO dataDTO){
        if(!taskDateCheck(dataDTO.taskValidity())) throw new IllegalArgumentException("The task's expiration date must be after today");
        this.textTask = dataDTO.textTask();
        this.taskValidity = formatTaskValidity(dataDTO.taskValidity());
        this.checkTask = false;
    }

    public TaskEntity(TaskDTO dataDTO) {
        BeanUtils.copyProperties(dataDTO, this);
    }

    public static LocalDate formatTaskValidity(String date){
        Objects.requireNonNull(date, "Date can't be NULL");
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static Boolean taskDateCheck(String date){
        LocalDate dateCheck = formatTaskValidity(date);
        if(dateCheck.isBefore(LocalDate.now())){
            return false;
        }
        return true;
    }

    public void setTextTask(String textTask) {
        Objects.requireNonNull(textTask, "Task cannot be null!");
        this.textTask = textTask;
    }

    public void setTaskValidity(LocalDate taskValidity) {
        Objects.requireNonNull(taskValidity, "Task validity cannot be null!");
        this.taskValidity = taskValidity;
    }
}
