package com.app.onemoretick.controller;

import com.app.onemoretick.model.dto.TaskDto;
import com.app.onemoretick.model.entity.Task;
import com.app.onemoretick.service.TaskService;
import com.app.onemoretick.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tasks/{user_id}")
@Validated
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasksForUser(@PathVariable(name = "user_id") Integer id){
        List<Task> tasksForUser = taskService.getAllTasksForUser(userService.getById(id));
        if(tasksForUser != null) {
            List<TaskDto> taskDtos = tasksForUser.stream()
                    .map(t -> modelMapper.map(t, TaskDto.class))
                    .toList();
            return new ResponseEntity<>(taskDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<TaskDto> addTaskForUser(@PathVariable(name = "user_id") Integer id, @Valid @RequestBody TaskDto newTaskRequest){

        newTaskRequest.setIdUser(id);
        Task task = taskService.addTask(modelMapper.map(newTaskRequest, Task.class));
        if(task != null)
            return new ResponseEntity<>(modelMapper.map(task, TaskDto.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateUserTask(@PathVariable(name = "user_id") Integer id, @Valid @RequestBody TaskDto updatedTaskRequest){

        updatedTaskRequest.setIdUser(id);
        Task task = taskService.updateTask(modelMapper.map(updatedTaskRequest, Task.class));

        if(task != null)
            return new ResponseEntity<>(modelMapper.map(task, TaskDto.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<TaskDto> getTaskForUser(@PathVariable(name = "user_id") Integer id, @PathVariable Integer task_id){
        Task taskForUser = taskService.getById(task_id);

        if(taskForUser != null && Objects.equals(taskForUser.getIdUser().getId(), id)) {
            TaskDto taskDto = modelMapper.map(taskForUser, TaskDto.class);
            return new ResponseEntity<>(taskDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<TaskDto> deleteTaskForUser(@PathVariable(name = "user_id") Integer id, @PathVariable Integer task_id){
        Task taskForUser = taskService.getById(task_id);
        if(taskForUser != null && Objects.equals(taskForUser.getIdUser().getId(), id)) {
            taskService.deleteTask(task_id);
            return new ResponseEntity<>(modelMapper.map(taskForUser, TaskDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
