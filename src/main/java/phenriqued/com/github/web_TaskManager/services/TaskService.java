package phenriqued.com.github.web_TaskManager.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phenriqued.com.github.web_TaskManager.entities.DTO.TaskDTO;
import phenriqued.com.github.web_TaskManager.entities.DTO.TaskRegistrationDTO;
import phenriqued.com.github.web_TaskManager.entities.TaskEntity;
import phenriqued.com.github.web_TaskManager.repositories.TaskRepository;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void createTask(TaskRegistrationDTO taskDTO){
        Objects.requireNonNull(taskDTO, "To save the task cannot be null");
        TaskEntity task = new TaskEntity(taskDTO);
        taskRepository.save(task);
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(taskEntity -> new TaskDTO(taskEntity)).toList();
    }

    public void updateTask(Long id, TaskRegistrationDTO dataDTO){
        TaskEntity oldTask = taskRepository.getById(id);
        updateDataTask(oldTask, dataDTO);
        taskRepository.save(oldTask);

    }

    public void deleteTask(Long id){
        if (!existById(id)){
            throw new IllegalArgumentException("There is no such task for deletion");
        }
        TaskEntity takeOutTask = taskRepository.getReferenceById(id);
        taskRepository.delete(takeOutTask);
    }


    private boolean existById(Long id){
        return taskRepository.existsById(id);
    }
    private void updateDataTask(TaskEntity oldTask, TaskRegistrationDTO newTask) {
        oldTask.setTextTask(newTask.textTask());
        if (newTask.taskValidity() != null) oldTask.setTaskValidity(TaskEntity.formatTaskValidity(newTask.taskValidity()));
    }

}
