package com.bookstore.authordetail.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@Setter
@Getter
public class AuthorDTO{

    String author;
    String bestseller;
    String bio;
}
