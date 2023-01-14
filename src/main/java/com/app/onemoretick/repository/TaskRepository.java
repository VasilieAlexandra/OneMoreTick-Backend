package com.app.onemoretick.repository;

import com.app.onemoretick.model.Task;
import com.app.onemoretick.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
List<Task> getAllByIdUser(User idUser);
}
