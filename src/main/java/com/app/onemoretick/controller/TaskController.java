package com.app.onemoretick.controller;

import com.app.onemoretick.model.Task;
import com.app.onemoretick.service.TaskService;
import com.app.onemoretick.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tasks/{user_id}")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasksForUser(@PathVariable(name = "user_id") Integer id){
        List<Task> tasksForUser = taskService.getAllTasksForUser(id);
        if(tasksForUser != null)
            return new ResponseEntity<>(tasksForUser, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<Task> addTaskForUser(@PathVariable(name = "user_id") Integer id, @RequestBody Task newTask){

        newTask.setIdUser(userService.getById(id));
        Task task = taskService.addTask(newTask);
        if(task != null)
            return new ResponseEntity<>(task, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Task> updateUserTask(@PathVariable(name = "user_id") Integer id,@RequestBody Task updatedTask){

        updatedTask.setIdUser(userService.getById(id));
        Task task = taskService.updateTask(updatedTask);
        if(task != null)
            return new ResponseEntity<>(task, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<Task> getTaskForUser(@PathVariable(name = "user_id") Integer id, @PathVariable Integer task_id){
        Task taskForUser = taskService.getById(task_id);
        if(taskForUser != null && Objects.equals(taskForUser.getIdUser().getId(), id))
            return new ResponseEntity<>(taskForUser, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<Task> deleteTaskForUser(@PathVariable(name = "user_id") Integer id, @PathVariable Integer task_id){
        Task taskForUser = taskService.getById(task_id);
        if(taskForUser != null && Objects.equals(taskForUser.getIdUser().getId(), id)) {
            taskService.deleteTask(task_id);
            return new ResponseEntity<>(taskForUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
