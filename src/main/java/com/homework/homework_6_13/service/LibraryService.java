package com.homework.homework_6_13.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homework.homework_6_13.domain.dto.AuthorDTO;
import com.homework.homework_6_13.domain.dto.BookDTO;
import com.homework.homework_6_13.domain.entity.Author;
import com.homework.homework_6_13.domain.entity.Book;
import com.homework.homework_6_13.repository.a.AuthorRepository;
import com.homework.homework_6_13.repository.a.BookRepository;

import jakarta.transaction.Transactional;

// implementation for "a" type database connection
@Service
public class LibraryService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public LibraryService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Optional<Author> getAuthor(long id) {
        return authorRepository.findById(id);
    }

    @Transactional
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public Optional<Book> getBook(long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(long id) {
       bookRepository.deleteById(id);
    }

    @Transactional
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }

    @Transactional
    public Set<Book> getBooksByAuthor(String firstName, String lastName) {
        Author author= authorRepository.findByFirstNameAndLastName(firstName, lastName);

        if (author == null) return new HashSet<Book>();
        else {
            return author.getBooks();
        }

    }

    @Transactional
    public Set<Author> getAuthorsByBook(String title, String year) {
        Book book = bookRepository.findByTitleAndYear(title, year);

        if (book == null) return new HashSet<>();
        else {
            return book.getAuthors();
        }
    }

    @Transactional
    public Author createAuthorWithBooks(String firstName, String lastName, List<BookDTO> bookDTOs) {

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        for (BookDTO bookDTO : bookDTOs) {
            Book book = bookRepository.findByTitleAndYear(bookDTO.getTitle(), bookDTO.getYear());

            if (book == null)
                book = new Book();
            book.setTitle(bookDTO.getTitle());

            author.getBooks().add(book);
        }

        return authorRepository.save(author);

    }

    @Transactional
    public Book createBookWithAuthors(String title, String year, List<AuthorDTO> authorDTOs) {

        Book book = new Book();
        book.setTitle(title);
        book.setYear(year);

        for (AuthorDTO authorDTO : authorDTOs) {
            Author author = authorRepository.findByFirstNameAndLastName(authorDTO.getFirstName(), authorDTO.getLastName());
            
            if (author == null) {
                author = new Author();
            }
            author.setFirstName(authorDTO.getFirstName());
            author.setLastName(authorDTO.getLastName());
            author.getBooks().add(book);
            authorRepository.save(author);
            book.getAuthors().add(author);
        }

        return bookRepository.save(book);

    }

}

// implementation for "b" type database connection
