package com.kulsin.model;

import java.util.Objects;

public class Book {
    private final String bookId;

    private final String title;

    public Book(String bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title);
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }
}
