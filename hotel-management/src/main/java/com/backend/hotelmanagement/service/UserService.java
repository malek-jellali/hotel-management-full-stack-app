package com.backend.hotelmanagement.service;

import com.backend.hotelmanagement.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);

}
