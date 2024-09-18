package com.kulsin;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
        if (id > 100) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return "User " + id;
    }

    @PostMapping("/users")
    public String createUser(@Valid @RequestBody User user) {
        return "User created: " + user.getName();
    }
}
