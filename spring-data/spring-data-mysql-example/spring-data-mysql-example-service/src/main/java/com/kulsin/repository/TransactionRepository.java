package com.kulsin.repository;

import com.kulsin.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Page<Transaction> findByTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<Transaction> findByTimeBetweenAndUserId(LocalDateTime start, LocalDateTime end, String userId, Pageable pageable);

}
