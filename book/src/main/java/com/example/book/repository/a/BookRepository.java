package com.homework.homework_6_13.repository.a;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homework.homework_6_13.domain.entity.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
    
    Book findByTitleAndYear(String title, String year);

}
