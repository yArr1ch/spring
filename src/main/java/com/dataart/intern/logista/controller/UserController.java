package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.security.NotFoundException;
import com.dataart.intern.logista.model.User;
import com.dataart.intern.logista.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> find() {
        return userService.find();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User findById(@PathVariable Integer id) {
        User user = userService.findById(id).
                orElseThrow(() -> new NotFoundException("Not found user with id = " + id));
        return user;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User update(@PathVariable Integer id, @Valid @RequestBody User user) {
        user.setId(id);
        return userService.update(user);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public User findByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }
}
