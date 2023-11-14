package com.todolist.todo;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tasks")
public class TaskController {

  private TaskRepository repository;

  public TaskController(TaskRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  public List<Task> getTasks() {
    return repository.findAll();
  }

  @PostMapping("")
  public Task createTask(@RequestBody Task task) {
    System.out.println(task.getId());
    return repository.save(task);
  }

  @PutMapping("/{id}")
  public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
    Optional <Task> taskToUpdate = repository.findById(id);
    if (taskToUpdate.isPresent()) {
      Task taskUpdated = taskToUpdate.get();
      taskUpdated.setContent(task.getContent());
      taskUpdated.setDone(task.isDone());
      return repository.save(taskUpdated);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found");
    }
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    Optional <Task> taskToDelete = repository.findById(id);
    if (taskToDelete.isPresent()) {
      repository.delete(taskToDelete.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found");
    }
  }





}
