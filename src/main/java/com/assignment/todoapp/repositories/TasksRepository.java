package com.assignment.todoapp.repositories;

import com.assignment.todoapp.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    Tasks findByTitle(final String title);

    List<Tasks> findAllByUser(final String user);
}
