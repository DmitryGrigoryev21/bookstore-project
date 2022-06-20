package com.bookstore.authordetail.services;

import com.bookstore.authordetail.controllers.AuthorDTO;
import com.bookstore.authordetail.entities.Author;
import com.bookstore.authordetail.entities.AuthorRepo;
import com.bookstore.authordetail.exceptions.GlobalExceptionHandler;
import com.bookstore.authordetail.exceptions.HttpErrorInfo;
import com.bookstore.authordetail.exceptions.InvalidInputException;
import com.bookstore.authordetail.mappers.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class AuthorDetailServiceImpl implements AuthorDetailService{

    private final AuthorRepo authorRepo;

    @Autowired
    private AuthorMapper authorMapper;

    public AuthorDetailServiceImpl(AuthorRepo authorRepo){
        this.authorRepo=authorRepo;
    }

    @Override
    public List<AuthorDTO> findAllAuthors() {

        List<Author> authors = (List<Author>) authorRepo.findAll();
        List<AuthorDTO> authorDTOS = authorMapper.entityListToResponseModelList(authors);
        return authorDTOS;
    }

    @Override
    public AuthorDTO findByAuthor(String Id) {
            Author author = authorRepo.findAuthorByAuthor(Id);
            return authorMapper.entityToModel(author);
    }

    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.modelToEntity(authorDTO);
        authorRepo.save(author);
        return authorMapper.entityToModel(author);
    }

    @Override
    public boolean deleteAuthor(String Id) {
        if (authorRepo.existsAuthorByAuthor(Id)) {
            authorRepo.deleteAuthorByAuthor(Id);
            return true;
        }
        else{
            throw new RuntimeException("Invalid bookUUID was provided.");
        }
    }

    @Override
    public AuthorDTO updateAuthor(String Id, AuthorDTO authorDTO) {
        if (authorRepo.existsAuthorByAuthor(Id)) {
            Author author = authorRepo.findAuthorByAuthor(Id);
            Author authorTemp = authorMapper.modelToEntity(authorDTO);
            author.setAuthor(authorTemp.getAuthor());
            author.setBestseller(authorTemp.getBestseller());
            author.setBio(authorTemp.getBio());
            authorRepo.save(author);
            return authorMapper.entityToModel(author);
        }
        else{
            throw new RuntimeException("Invalid author was provided.");
        }
    }
}
