package com.app.onemoretick.service.impl;

import com.app.onemoretick.model.User;
import com.app.onemoretick.repository.UserRepository;
import com.app.onemoretick.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        List<User> users = userRepository.findAll();
        Predicate<User> emailPredicate = u -> u.getEmail().equals(user.getEmail());
        Optional<User> copyUser = users.stream().filter(emailPredicate).findFirst();
        if (copyUser.isPresent())
            return null;
        return userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);
    }

    @Override
    public User updateUser(User user) {
        User userFromDb = getByEmail(user.getEmail());
        if(userFromDb != null) {
            userFromDb.setEmail(user.getEmail());
            userFromDb.setPassword(user.getPassword());
            userFromDb.setTasks(user.getTasks());
            userFromDb.setShoppingLists(user.getShoppingLists());
            return userRepository.save(userFromDb);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
    }

    @Override
    public User logUser(User user) {
        List<User> users = userRepository.findAll();
        Predicate<User> passwordPredicate = u -> u.getPassword().equals(user.getPassword());
        Predicate<User> emailPredicate = u -> u.getEmail().equals(user.getEmail());
        Optional<User> loggedUser = users.stream().filter(passwordPredicate.and(emailPredicate)).findFirst();
        return loggedUser.orElse(null);
    }
}
