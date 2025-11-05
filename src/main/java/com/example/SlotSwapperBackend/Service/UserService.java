package com.example.SlotSwapperBackend.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SlotSwapperBackend.Model.User;
import com.example.SlotSwapperBackend.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor injection
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        // Check if email already exists
        Optional<User> existing = userRepo.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }

        // ✅ Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

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
