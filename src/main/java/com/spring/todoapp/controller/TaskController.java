package com.spring.todoapp.controller;

import com.spring.todoapp.persistence.entity.Task;
import com.spring.todoapp.persistence.entity.TaskStatus;
import com.spring.todoapp.service.TaskService;
import com.spring.todoapp.service.dto.TaskInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ingeniero Daniel Camacho Fonseca
 * 
 */

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task CreateTask(@RequestBody TaskInDto taskInDto){
        return this.taskService.CreateTask(taskInDto);
    }

    @GetMapping
    public List<Task> findAll(){
        return this.taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByStatus(@PathVariable("status") TaskStatus status){
        return this.taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
