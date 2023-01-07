package com.app.onemoretick.service.impl;

import com.app.onemoretick.models.Task;
import com.app.onemoretick.models.User;
import com.app.onemoretick.repository.UserRepository;
import com.app.onemoretick.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public User updateUser(User user) {
        User userFromDb = getById(user.getId());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setTasks(user.getTasks());
        return userRepository.save(userFromDb);

    }

    @Override
    public void deleteUser(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
    }
}
