package com.homework.homework_6_13.ServiceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.homework.homework_6_13.domain.entity.Book;
import com.homework.homework_6_13.repository.a.AuthorRepository;
import com.homework.homework_6_13.repository.a.BookRepository;
import com.homework.homework_6_13.service.LibraryService;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {
    
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    public void testGetBooksByAuthor() {
        // Arrange
        String firstName = "David";
        String lastName = "Webb";
        
        Book mockBook = new Book();
        mockBook.setId(13);
        mockBook.setTitle("lightning");
        mockBook.setYear("2000");

        Set<Book> mockBookSet = new HashSet<>();
        mockBookSet.add(mockBook);

        when(libraryService.getBooksByAuthor(firstName, lastName)).thenReturn(mockBookSet);

        // Act

        Set<Book> retrievedBookSet = libraryService.getBooksByAuthor(firstName, lastName);

        // Research

        assertEquals(mockBookSet, retrievedBookSet);
        verify(libraryService, times(1)).getBooksByAuthor(firstName, lastName);
    }

}
