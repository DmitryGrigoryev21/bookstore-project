package com.bookstore.bookreview.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO extends RepresentationModel<ReviewDTO> {

    public String bookUUID;
    public String review;
    public int rating;
}
