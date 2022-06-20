package com.grigoryev.bookstore.services;

import com.grigoryev.bookstore.controllers.BookLongResponseDTO;
import com.grigoryev.bookstore.controllers.BookRequestDTO;
import com.grigoryev.bookstore.controllers.BookShortResponseDTO;
import com.grigoryev.bookstore.entities.Book;
import com.grigoryev.bookstore.entities.BookRepo;
import com.grigoryev.bookstore.mappers.BookLongResponseMapper;
import com.grigoryev.bookstore.mappers.BookRequestMapper;
import com.grigoryev.bookstore.mappers.BookShortResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepo bookRepo;

    @Autowired
    private BookRequestMapper bookRequestMapper;

    @Autowired
    private BookLongResponseMapper bookLongResponseMapper;

    @Autowired
    private BookShortResponseMapper bookShortResponseMapper;

    BookServiceImpl(BookRepo bookRepo){
        this.bookRepo=bookRepo;
    }

    @Override
    public List<BookLongResponseDTO> findAllBooksLong() {

        List<Book> books = (List<Book>) bookRepo.findAll();
        List<BookLongResponseDTO> bookLongResponseDTOS = bookLongResponseMapper.entityListToResponseModelList(books);
        return bookLongResponseDTOS;
    }

    @Override
    public List<BookShortResponseDTO> findAllBooksShort() {

        List<Book> books = (List<Book>) bookRepo.findAll();
        List<BookShortResponseDTO> bookShortResponseDTOS = bookShortResponseMapper.entityListToResponseModelList(books);
        return bookShortResponseDTOS;
    }

    @Override
    public BookLongResponseDTO findByBookUUID(String Id) {
        Book book = bookRepo.findBookByBookUUID(Id);
        return bookLongResponseMapper.entityToModel(book);
    }

    @Override
    public BookLongResponseDTO saveBook(BookRequestDTO bookRequestDTO) {
        Book book = bookRequestMapper.modelToEntity(bookRequestDTO);
        UUID uuid = UUID.randomUUID();
        book.setBookUUID(uuid.toString());
        bookRepo.save(book);
        return bookLongResponseMapper.entityToModel(book);
    }

    @Transactional
    @Override
    public boolean deleteBookByBookUUID(String Id) {
        if (bookRepo.existsBookByBookUUID(Id)) {
            bookRepo.deleteBookByBookUUID(Id);
            return true;
        }
        else{
            throw new EntityNotFoundException("Invalid bookUUID was provided.");
        }
    }

    @Override
    public BookLongResponseDTO updateBookByBookUUID(String Id, BookRequestDTO newBook) {
        if (bookRepo.existsBookByBookUUID(Id)) {
            Book book = bookRepo.findBookByBookUUID(Id);
            Book bookTemp = bookRequestMapper.modelToEntity(newBook);
            book.setTitle(bookTemp.getTitle());
            book.setAuthor(bookTemp.getAuthor());
            book.setSummary(bookTemp.getSummary());
            book.setIsbn(bookTemp.getIsbn());
            book.setPrice(bookTemp.getPrice());
            bookRepo.save(book);
            return bookLongResponseMapper.entityToModel(book);
        }
        else{
            throw new EntityNotFoundException("Invalid bookUUID was provided.");
        }
    }
}
