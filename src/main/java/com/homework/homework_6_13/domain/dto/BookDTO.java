package com.homework.homework_6_13.domain.dto;

import java.util.Set;

import com.homework.homework_6_13.domain.entity.Author;

public class BookDTO {
    
    private long id;
    private String title;
    private String year;
    private Set<Author> authors;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

}
