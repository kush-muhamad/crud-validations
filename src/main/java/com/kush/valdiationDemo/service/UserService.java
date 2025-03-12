package com.kush.valdiationDemo.service;

import com.kush.valdiationDemo.exceptions.NotFoundException;
import com.kush.valdiationDemo.model.User;
import com.kush.valdiationDemo.repo.UserRepo;
import jakarta.validation.Valid;
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
        return user.orElseThrow(() -> new NotFoundException("User not found with id: " + id));

    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(Long id, @Valid User user) {
        Optional<User> existingUser = userRepo.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            return userRepo.save(updatedUser);
        } else {
            throw new NotFoundException("User not found with id: " + id);
        }
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.delete(user.get());
        } else {
            throw new NotFoundException("User not found with id: " + id);
        }
    }
}
