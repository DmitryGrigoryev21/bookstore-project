package com.bookstore.apigateway.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ReviewDTO extends RepresentationModel<BookAggregateDTO> {
    String bookUUID;
    String review;
    int rating;
}
