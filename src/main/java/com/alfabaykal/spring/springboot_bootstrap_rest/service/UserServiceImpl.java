package com.alfabaykal.spring.springboot_bootstrap_rest.service;

import com.alfabaykal.spring.springboot_bootstrap_rest.dao.UserRepository;
import com.alfabaykal.spring.springboot_bootstrap_rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(user.getPassword().isEmpty() ?
                userRepository.findUserByUsername(user.getUsername()).getPassword()
                : passwordEncoder.encode(user.getPassword()));
//        String password = user.getPassword();
//        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }
}