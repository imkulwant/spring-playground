package com.kulsin.resource;

import com.kulsin.entity.QueryRequestBody;
import com.kulsin.entity.User;
import com.kulsin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchElementException("No such user");
        }
    }

    @GetMapping(value = "/user")
    public List<User> getUserLoggedInBetween(@RequestBody QueryRequestBody request) {

        LocalDateTime start = LocalDateTime.parse(request.getFrom());
        LocalDateTime end = LocalDateTime.parse(request.getTo());
        Pageable limit = PageRequest.of(0, 10);

        return userRepository.findByLoginTimeBetween(start, end, limit);
    }

    @PostMapping(value = "/user")
    public User getUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}
