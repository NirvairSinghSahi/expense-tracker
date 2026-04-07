package com.example.expense.service;

import com.example.expense.model.User;
import com.example.expense.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public boolean register(User user) {

        // check if username exists
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }

        repo.save(user);
        return true;
    }

    public User login(String username, String password) {
        Optional<User> user = repo.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;
    }
}