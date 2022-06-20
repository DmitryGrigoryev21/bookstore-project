package com.bookstore.apigateway.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class AuthorDetailDTO extends RepresentationModel<AuthorDetailDTO>{
    String author;
    String bestseller;
    String bio;
}
