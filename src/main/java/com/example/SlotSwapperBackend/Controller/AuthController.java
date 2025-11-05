package com.example.SlotSwapperBackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.SlotSwapperBackend.Model.User;
import com.example.SlotSwapperBackend.Service.UserService;
import com.example.SlotSwapperBackend.Security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register using params
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    // ✅ Login using params
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam String password) {

        try {
            // This will use your CustomUserDetailsService + PasswordEncoder
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            // Generate JWT
            String token = jwtUtil.generateToken(email);

            return ResponseEntity.ok(token);

        } catch (Exception ex) {
            return ResponseEntity.status(403).body("Invalid email or password");
        }
    }
}