package com.app.onemoretick.service.impl;

import com.app.onemoretick.model.Task;
import com.app.onemoretick.model.User;
import com.app.onemoretick.repository.TaskRepository;
import com.app.onemoretick.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getById(Integer id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.orElse(null);
    }

    @Override
    public Task updateTask(Task task) {
        Task taskFromDb = getById(task.getId());
        taskFromDb.setTitle(task.getTitle());
        taskFromDb.setDescription(task.getDescription());
        taskFromDb.setStartDate(task.getStartDate());
        taskFromDb.setEndDate(task.getEndDate());
        taskFromDb.setIsDone(task.getIsDone());
        taskFromDb.setIdCategory(task.getIdCategory());
        taskFromDb.setIdUser(task.getIdUser());
        return taskRepository.save(taskFromDb);

    }

    @Override
    public void deleteTask(Integer id) {
        Task task = getById(id);
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getAllTasksForUser(User user) {
        return taskRepository.getAllByIdUser(user);
    }
}
