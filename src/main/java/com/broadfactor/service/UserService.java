package com.broadfactor.service;

import com.broadfactor.model.User;

import java.util.List;

public interface UserService {

    User insert(User user);

    List<User> findAll();

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);
}
