package com.course.hotelApi.service;

import com.course.hotelApi.dto.UserDto;
import com.course.hotelApi.entity.User;
import com.course.hotelApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User findBy(String username) {
        return userRepository.findByUsername(username);
    }

    public User update(Integer id, User user) {
        user.setId(id);
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public User findById(Integer id) {
        return getById(id);
    }

    private User getById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserDto map(User user) {
        UserDto u = new UserDto();
        u.setUserId(user.getId());
        u.setUsername(user.getUsername());
        return u;
    }
}
