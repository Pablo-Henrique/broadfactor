package com.broadfactor.service.impl;

import com.broadfactor.model.User;
import com.broadfactor.repository.UserRepository;
import com.broadfactor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User insert(User user) {
        return repository.save(user);
    }
}
