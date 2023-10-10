package phenriqued.com.github.web_TaskManager.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phenriqued.com.github.web_TaskManager.entities.DTO.TaskDTO;
import phenriqued.com.github.web_TaskManager.entities.DTO.TaskRegistrationDTO;
import phenriqued.com.github.web_TaskManager.services.TaskService;

import java.util.List;

@Controller
@RequestMapping("taskManager/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> loadAllTasks(){
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<TaskRegistrationDTO> createTask(@RequestBody TaskRegistrationDTO task){
        taskService.createTask(task);
        return ResponseEntity.ok().body(task);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskRegistrationDTO> updateTask(@PathVariable Long id ,@RequestBody TaskRegistrationDTO task){
        taskService.updateTask(id,task);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
