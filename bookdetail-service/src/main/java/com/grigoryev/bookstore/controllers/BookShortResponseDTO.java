package com.grigoryev.bookstore.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class BookShortResponseDTO{

    String bookUUID;
    String title;
    String author;
    double price;
}
