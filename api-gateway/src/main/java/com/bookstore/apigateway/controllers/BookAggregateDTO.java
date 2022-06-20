package com.bookstore.apigateway.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Setter
@Getter
public class BookAggregateDTO extends RepresentationModel<BookAggregateDTO> {
    public String bookUUID;
    public String title;
    public String author;
    public String summary;
    public String isbn;
    public double price;
    public List<ReviewDTO> review;
    public AuthorDetailDTO authorDetail;
    public List<TagDTO> tag;
}
