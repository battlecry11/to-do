package ge.softlab.todo.controller;

import ge.softlab.todo.model.Task;
import ge.softlab.todo.repository.TaskRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository taskRepository;

    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
   @PostMapping
   public ResponseEntity<Task> addTask(@RequestBody Task task){
       // task.setId(null);
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
  }
  @PutMapping("{id}")
    public  Task updateTask(@PathVariable long id, @RequestBody  Task unsafeTask) throws NotFoundException {
        Task task=getTask(id);
        task.setTask(unsafeTask.getTask());
        return taskRepository.save(task);
  }
  @GetMapping("{id")
    public Task getTask(@PathVariable long id ) throws NotFoundException {
        return taskRepository.findById(id).orElseThrow(()-> new NotFoundException("Task Not Found"));
  }
  @DeleteMapping("{id}")
    public void deleteTask(@PathVariable long id) throws NotFoundException {
        Task task=getTask(id);
        taskRepository.delete(task);
  }

}

