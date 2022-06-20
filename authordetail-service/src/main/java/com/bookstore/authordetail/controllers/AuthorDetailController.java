package com.bookstore.authordetail.controllers;

import com.bookstore.authordetail.services.AuthorDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorDetailController {
    @Autowired
    private AuthorDetailService authorDetailService;

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDTO>> listAllAuthors(){
        return ResponseEntity.status(HttpStatus.OK).body(authorDetailService.findAllAuthors());
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<AuthorDTO> listAuthorByAuthor(@PathVariable String author){
        return ResponseEntity.status(HttpStatus.OK).body(authorDetailService.findByAuthor(author));
    }

    @PostMapping("/author")
    public ResponseEntity<AuthorDTO> newAuthor(@RequestBody AuthorDTO authorDTO)
    {
        try {
            AuthorDTO authorDTO1 = authorDetailService.saveAuthor(authorDTO);
            return ResponseEntity.status(HttpStatus.OK).body(authorDTO1);
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @DeleteMapping("/author/{Id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable String Id) {
        boolean success = authorDetailService.deleteAuthor(Id);
        String response;
        if (success)
            response = "Success";
        else
            response = "Aborted";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/author/{Id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable String Id) {
        return ResponseEntity.status(HttpStatus.OK).body(authorDetailService.updateAuthor(Id,authorDTO));
    }
}
