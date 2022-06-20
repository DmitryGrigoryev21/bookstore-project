package com.bookstore.authordetail.services;

import com.bookstore.authordetail.controllers.AuthorDTO;

import java.util.List;

public interface AuthorDetailService {
    public List<AuthorDTO> findAllAuthors();
    public AuthorDTO findByAuthor(String Id);
    public AuthorDTO saveAuthor(AuthorDTO authorDTO);
    public boolean deleteAuthor(String Id);
    public AuthorDTO updateAuthor(String Id, AuthorDTO authorDTO);
}
