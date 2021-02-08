package com.assignment.todoapp.repositories;

import com.assignment.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(final String email);

}
