package com.app.onemoretick.service;

import com.app.onemoretick.models.User;

public interface UserService {
    User addUser(User user);
    User getById(Integer id);
    User updateUser(User user);
    void deleteUser(Integer id);
}
