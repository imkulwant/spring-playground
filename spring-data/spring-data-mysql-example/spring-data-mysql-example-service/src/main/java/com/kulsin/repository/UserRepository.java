package com.kulsin.repository;

import com.kulsin.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByLoginTimeBetween(LocalDateTime start, LocalDateTime end, Pageable limit);

}
