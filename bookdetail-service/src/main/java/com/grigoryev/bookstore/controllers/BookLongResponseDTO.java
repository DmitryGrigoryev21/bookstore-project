package com.grigoryev.bookstore.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@Setter
@Getter
public class BookLongResponseDTO extends RepresentationModel<BookLongResponseDTO> {

    String bookUUID;
    String title;
    String author;
    String summary;
    String isbn;
    double price;
}
