package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.security.JwtUtils;
import com.dataart.intern.logista.model.LoginRequest;
import com.dataart.intern.logista.model.LoginResponse;
import com.dataart.intern.logista.model.User;
import com.dataart.intern.logista.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public LoginResponse res(@RequestBody LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername());
        passwordEncoder.matches(req.getPassword(), user.getPassword());
        String token = jwtUtils.generateToken(user.getUsername());
        return new LoginResponse(token);
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public User registerHandler(@RequestBody User user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user = userRepository.save(user);
        return user;
    }
}
