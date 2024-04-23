package com.kulsin.model;

import java.util.Objects;

public class BookOrder {

    private final String bookOrderId;
    private final Customer customer;
    private final Book book;

    public BookOrder(String bookOrderId, Customer customer, Book book) {
        this.bookOrderId = bookOrderId;
        this.customer = customer;
        this.book = book;
    }

    public String getBookOrderId() {
        return bookOrderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrder bookOrder = (BookOrder) o;
        return Objects.equals(bookOrderId, bookOrder.bookOrderId) &&
                Objects.equals(customer, bookOrder.customer) &&
                Objects.equals(book, bookOrder.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookOrderId, customer, book);
    }
}
