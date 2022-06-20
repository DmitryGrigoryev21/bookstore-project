package com.bookstore.authordetail.services;

import com.bookstore.authordetail.controllers.AuthorDTO;
import com.bookstore.authordetail.entities.Author;
import com.bookstore.authordetail.entities.AuthorRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthorDetailServiceImplTest {
//
//    @Autowired
//    AuthorDetailService authorDetailService;
//
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @MockBean
//    AuthorRepo repo;
//
//    @SpyBean
//    AuthorDTO mapper;
//
//    @AfterEach
//    void tearDown() {
//
//        repo.deleteAll();
//    }
//
//    @Test
//    void findAll() {
//
//        List<AuthorDTO> authorDTOS;
//
//        authorDTOS = authorDetailService.findAllAuthors();
//
//        assertNotNull(authorDTOS);
//    }
//
//    @Test
//    void findAuthor() {
//
//        Author author = new Author();
//        AuthorDTO model;
//        author.setAuthor("dsge");
//        author.setBio("dsge");
//        author.setBestseller("dsge");
//
//        repo.save(author);
//
//        when(repo.save(any(Author.class))).thenAnswer(i -> i.getArguments()[0]);
//        when(repo.existsAuthorByAuthor(author.getAuthor())).thenReturn(true);
//        when(repo.findAuthorByAuthor(author.getAuthor())).thenReturn(author);
//
//        model = authorDetailService.findByAuthor(author.getAuthor());
//
//        assertNotNull(model);
//    }
//    @Test
//    void newAuthor() {
//
//        AuthorDTO authorDTO = new AuthorDTO();
//        AuthorDTO model;
//
//        authorDTO.setAuthor("gergherh");
//
//        model = authorDetailService.saveAuthor(authorDTO);
//
//        assertNotNull(model);
//
//
//    }
}