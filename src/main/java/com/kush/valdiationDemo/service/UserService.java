package com.kush.valdiationDemo.service;

import com.kush.valdiationDemo.model.User;
import com.kush.valdiationDemo.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    }

    public User addUser(User user) {
        return userRepo.save(user);
    }
}
