package com.example.SlotSwapperBackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.SlotSwapperBackend.Model.User;
import com.example.SlotSwapperBackend.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // // ✅ Register a new user using URL parameters
    // @PostMapping("/register")
    // public User registerUser(
    // @RequestParam String name,
    // @RequestParam String email,
    // @RequestParam String password) {
    // User user = new User();
    // user.setName(name);
    // user.setEmail(email);
    // user.setPassword(password);

    // return userService.registerUser(user);
    // }

    // ✅ Get user by email
    @GetMapping
    public User getUser(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    // ✅ Update user using params
    @PutMapping("/update")
    public User updateUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        return userService.updateUser(user);
    }

    // ✅ Delete user using email
    @DeleteMapping
    public String deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        return "User deleted";
    }
}
