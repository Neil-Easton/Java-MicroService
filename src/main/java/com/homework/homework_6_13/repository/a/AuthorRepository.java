package com.homework.homework_6_13.repository.a;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.homework.homework_6_13.domain.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByFirstNameAndLastName(String firstName, String lastName);

}
