package com.spring.todoapp.persistence.repository;

import com.spring.todoapp.persistence.entity.Task;
import com.spring.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findAllByTaskStatus(TaskStatus status);

    @Modifying
    @Query(value = "UPDATE TASK SET FINISHED=True WHERE ID=:id", nativeQuery = true)
    public void markTaskFinished(@Param("id") Long id);

}
