package com.grigoryev.bookstore.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BookRequestDTO {

    String title;
    String author;
    String summary;
    String isbn;
    double price;
}
