package com.grigoryev.bookstore.controllers;

import com.grigoryev.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books/detailed")
    public ResponseEntity<List<BookLongResponseDTO>> listBooksAllLong(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooksLong());
    }

    @GetMapping("/books/short")
    public ResponseEntity<List<BookShortResponseDTO>> listBooksAllShort(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooksShort());
    }

    @GetMapping("/book/{Id}")
    public ResponseEntity<BookLongResponseDTO> listBookByBookUUID(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findByBookUUID(Id));
    }

    @PostMapping("/book")
    public ResponseEntity<BookLongResponseDTO> newBook(@RequestBody BookRequestDTO book)
    {
        try {
            BookLongResponseDTO newBook = bookService.saveBook(book);
            return ResponseEntity.status(HttpStatus.OK).body(newBook);
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @DeleteMapping("/book/{Id}")
    public ResponseEntity<String> deleteBookByBookUUID(@PathVariable String Id) {
        boolean success = bookService.deleteBookByBookUUID(Id);
        String response;
        if (success)
            response = "Success";
        else
            response = "Aborted";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/book/{Id}")
    public ResponseEntity<BookLongResponseDTO> updateBookByBookUUID(@RequestBody BookRequestDTO book, @PathVariable String Id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBookByBookUUID(Id,book));
    }
}
