package com.benswenson.school.service;

import java.util.List;

import com.benswenson.school.entity.User;

public interface UserService {

    User getUser(Long id);
    User getUser(String username);
    User createUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
    
}
