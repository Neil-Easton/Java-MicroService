package com.homework.homework_6_13.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.homework_6_13.domain.dto.AuthorDTO;
import com.homework.homework_6_13.domain.dto.BookDTO;
import com.homework.homework_6_13.domain.entity.Author;
import com.homework.homework_6_13.domain.entity.Book;
import com.homework.homework_6_13.service.LibraryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/authors/{firstName}/{lastName}")
    public Set<Book> getBooksByAuthor(@PathVariable String firstName, @PathVariable String lastName) {
        return libraryService.getBooksByAuthor(firstName, lastName);
    }

    @GetMapping("/books/{title}/{year}")
    public Set<Author> getAuthorsByBook(@PathVariable String title, @PathVariable String year) {
        return libraryService.getAuthorsByBook(title, year);
    }
    

    @PostMapping("/authors/{firstName}/{lastName}")
    public Author createAuthor(@PathVariable String firstName, @PathVariable String lastName,
            @RequestBody List<BookDTO> books) {

        return libraryService.createAuthorWithBooks(firstName, lastName, books);

    }

    @PostMapping("/books/{title}/{year}")
    public Book createBook(@PathVariable String title, @PathVariable String year, @RequestBody List<AuthorDTO> authors) {
        return libraryService.createBookWithAuthors(title, year, authors);
    }

    @PutMapping("/authors/update")
    public Author updateAuthor(@RequestBody AuthorDTO authorDTO) {
        Optional<Author> author = libraryService.getAuthor(authorDTO.getId());

        if (!author.isPresent()) return null;
        else {
            author.get().setFirstName(authorDTO.getFirstName());
            author.get().setLastName(authorDTO.getLastName());

            return libraryService.saveAuthor(author.get());
        }
    }

    @PutMapping("/books/update")
    public Book updateBook(@RequestBody BookDTO bookDTO) {

        Optional<Book> book = libraryService.getBook(bookDTO.getId());

        if (!book.isPresent()) return null;
        else {
            book.get().setTitle(bookDTO.getTitle());
            book.get().setYear(bookDTO.getYear());

            return libraryService.saveBook(book.get());
        }
    }

    @DeleteMapping("/authors/delete/{id}")
    public void deleteAuthor(@PathVariable long id) {
        Optional<Author> author = libraryService.getAuthor(id);

        if (author.isPresent()) {
            libraryService.deleteAuthor(author.get().getId());
        }

    }

    @DeleteMapping("/books/delete/{id}")
    public void deleteBook(@PathVariable long id) {
        Optional<Book> book = libraryService.getBook(id);

        if (book.isPresent()) {
            libraryService.deleteBook(book.get().getId());
        }
    }


    
}
