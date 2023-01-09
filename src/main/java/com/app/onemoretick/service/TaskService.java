package com.app.onemoretick.service;

import com.app.onemoretick.model.Task;

public interface TaskService {
Task addTask(Task task);
Task getById(Integer id);
Task updateTask(Task task);
void deleteTask(Integer id);
}
