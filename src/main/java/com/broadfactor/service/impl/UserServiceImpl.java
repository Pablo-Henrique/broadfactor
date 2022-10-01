package com.broadfactor.service.impl;

import com.broadfactor.model.User;
import com.broadfactor.repository.UserRepository;
import com.broadfactor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public User insert(User user) {
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new DataIntegrityViolationException("Usuário ou email já cadastrado!");
        }
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
