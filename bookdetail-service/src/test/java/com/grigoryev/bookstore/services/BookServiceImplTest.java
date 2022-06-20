package com.grigoryev.bookstore.services;

import com.grigoryev.bookstore.controllers.BookLongResponseDTO;
import com.grigoryev.bookstore.controllers.BookRequestDTO;
import com.grigoryev.bookstore.entities.Book;
import com.grigoryev.bookstore.entities.BookRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @MockBean
    BookRepo repo;

    @SpyBean
    BookLongResponseDTO mapper;

    @SpyBean
    BookRequestDTO mapper2;

    @AfterEach
    void tearDown() {

        repo.deleteAll();
    }

    @Test
    void findAll() {

        List<BookLongResponseDTO> bookLongResponseDTOS;

        bookLongResponseDTOS = bookService.findAllBooksLong();

        assertNotNull(bookLongResponseDTOS);
    }

    @Test
    void findTag() {

        Book book = new Book();
        BookLongResponseDTO model;
        book.setBookUUID("3425");
        book.setTitle("dsge");
        book.setAuthor("dsge");
        book.setSummary("dsge");
        book.setIsbn("dsge");
        book.setPrice(45);

        repo.save(book);

        when(repo.save(any(Book.class))).thenAnswer(i -> i.getArguments()[0]);
        when(repo.existsBookByBookUUID(book.getBookUUID())).thenReturn(true);
        when(repo.findBookByBookUUID(book.getBookUUID())).thenReturn(book);

        model = bookService.findByBookUUID(book.getBookUUID());

        assertNotNull(model);
    }
    @Test
    void newBook() {

        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        BookLongResponseDTO model;

        bookRequestDTO.setAuthor("gergherh");

        model = bookService.saveBook(bookRequestDTO);

        assertNotNull(model);


    }
}