package com.bookstore.apigateway.aggregates;

import com.bookstore.apigateway.controllers.AuthorDetailDTO;
import com.bookstore.apigateway.controllers.ReviewDTO;
import com.bookstore.apigateway.controllers.TagDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class BookAggregate {
    public Integer id;
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
