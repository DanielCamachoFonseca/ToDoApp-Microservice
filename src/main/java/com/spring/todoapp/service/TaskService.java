package com.spring.todoapp.service;

import com.spring.todoapp.exceptions.ToDoExceptions;
import com.spring.todoapp.mapper.TaskInDTOToTask;
import com.spring.todoapp.persistence.entity.Task;
import com.spring.todoapp.persistence.entity.TaskStatus;
import com.spring.todoapp.persistence.repository.TaskRepository;
import com.spring.todoapp.service.dto.TaskInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    /**
     * Metodo que realiza la inyeccion de dependencias por medio del constructor
     * @param repository
     * @param mapper
     */
    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Metodo que permite guardar un nuevo Task, mapeando el objeto de taskInDto a task
     * @param taskInDto
     * @return
     */
    public Task CreateTask(TaskInDto taskInDto){
        Task task = mapper.map(taskInDto);
        return this.repository.save(task);
    }

    /**
     * Metodo que permite listar todos los registros de la base de datos, por medio de la interfaz JPARepository
     * @return List<Task>
     */
    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task Not Found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskFinished(id);
    }

    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task Not Found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }




}
