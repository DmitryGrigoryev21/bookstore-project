package com.grigoryev.bookstore.services;

import com.grigoryev.bookstore.controllers.BookLongResponseDTO;
import com.grigoryev.bookstore.controllers.BookRequestDTO;
import com.grigoryev.bookstore.controllers.BookShortResponseDTO;

import java.util.List;

public interface BookService {
    public List<BookLongResponseDTO> findAllBooksLong();
    public List<BookShortResponseDTO> findAllBooksShort();
    public BookLongResponseDTO findByBookUUID(String Id);
    public BookLongResponseDTO saveBook(BookRequestDTO bookRequestDTO);
    public boolean deleteBookByBookUUID(String Id);
    public BookLongResponseDTO updateBookByBookUUID(String Id, BookRequestDTO newBook);
}
