package com.app.onemoretick.service;

import com.app.onemoretick.model.User;

public interface UserService {
    User addUser(User user);
    User getById(Integer id);

    User getByEmail(String email);

    User updateUser(User user);
    void deleteUser(Integer id);
    User logUser(User user);
}
