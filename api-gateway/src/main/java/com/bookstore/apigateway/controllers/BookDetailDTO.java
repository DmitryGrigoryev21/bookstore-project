package com.bookstore.apigateway.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
public class BookDetailDTO extends RepresentationModel<BookAggregateDTO>{
    String bookUUID;
    String title;
    String author;
    String summary;
    String isbn;
    double price;
}
