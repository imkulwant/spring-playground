package com.kulsin.model;


import java.util.Objects;

public class Customer {
    private final String customerId;
    private final String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name);
    }
}
