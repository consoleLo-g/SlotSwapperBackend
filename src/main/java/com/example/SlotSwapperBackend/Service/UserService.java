package com.example.SlotSwapperBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SlotSwapperBackend.Model.User;
import com.example.SlotSwapperBackend.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User registerUser(User user) {
        // Check if email already exists
        Optional<User> existing = userRepo.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }

        return userRepo.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(String email) {
        User user = getUserByEmail(email);
        userRepo.delete(user);
    }
}
