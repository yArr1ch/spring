package com.course.hotelApi.controller;

import com.course.hotelApi.entity.User;
import com.course.hotelApi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    @PreAuthorize("permitAll()")
    public User findBy(@PathVariable String username) {
        return userService.findBy(username);
    }

    @PutMapping("/{id}")
    @PreAuthorize("permitAll()")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("permitAll()")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }
}
