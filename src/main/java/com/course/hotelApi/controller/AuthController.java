package com.course.hotelApi.controller;

import com.course.hotelApi.entity.LoginRequest;
import com.course.hotelApi.entity.LoginResponse;
import com.course.hotelApi.entity.User;
import com.course.hotelApi.repository.UserRepository;
import com.course.hotelApi.security.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final Jwt jwt;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        passwordEncoder.matches(user.getPassword(), request.getPassword());
        String token = jwt.createToken(user.getUsername());
        return new LoginResponse(token);
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public User register(@RequestBody User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return userRepository.save(user);
    }
}
