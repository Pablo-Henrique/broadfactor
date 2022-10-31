package com.broadfactor.service.impl;

import com.broadfactor.handler.exceptions.EntityNotFoundException;
import com.broadfactor.model.User;
import com.broadfactor.repository.UserRepository;
import com.broadfactor.service.EmailService;
import com.broadfactor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.NESTED)
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    @Override
    public User insert(User user) {
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new DataIntegrityViolationException("Usuário ou email já cadastrado!");
        }
        emailService.systemMailSender(user.getEmail());
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }
}
