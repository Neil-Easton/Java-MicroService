package com.homework.homework_6_13.domain.dto;

import java.util.Set;

import com.homework.homework_6_13.domain.entity.Book;

public class AuthorDTO {
    private long id;
    private String firstName;
    private String lastName;
    private Set<Book> books;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

}
