package com.kulsin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "TXN_ID")
    private UUID txId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "TIME")
    private LocalDateTime time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transaction that = (Transaction) o;
        return txId != null && Objects.equals(txId, that.txId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}