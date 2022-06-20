package com.bookstore.apigateway.services;

import com.bookstore.apigateway.aggregates.BookAggregate;
import com.bookstore.apigateway.controllers.*;

import java.util.List;

public interface BookAggregateService {
    public BookAggregateDTO listBookByBookUUID(String Id);
    public List<BookAggregateDTO> listBookAggregates();
    public BookAggregateDTO saveBookAggregate(BookAggregateDTO bookAggregateDTO);
    public BookAggregateDTO deleteBookAggregate(String Id);
    public BookAggregateDTO updateBookAggregate(BookAggregateDTO bookAggregateDTO, String Id);

    public BookDetailDTO listBookOnlyByBookUUID(String Id);
    public List<BookDetailDTO> listBooks();
    public BookDetailDTO saveBook(BookDetailDTO bookDetailDTO);
    public BookDetailDTO deleteBook(String Id);
    public BookDetailDTO updateBook(BookDetailDTO bookDetailDTO, String Id);

    public List<AuthorDetailDTO> listAllAuthors();
    public AuthorDetailDTO listAuthor(String Id);
    public AuthorDetailDTO saveAuthor(AuthorDetailDTO authorDetailDTO);
    public AuthorDetailDTO deleteAuthor(String Id);
    public AuthorDetailDTO updateAuthor(AuthorDetailDTO authorDetailDTO, String Id);

    public List<TagDTO> listAllTags();
    public List<TagDTO> listTags(String Id);
    public TagDTO saveTag(TagDTO tagDTO);
    public List<String> listUniqueTags();
    public List<String> listRecommended(String Id);

    public List<ReviewDTO> listAllReviews();
    public List<ReviewDTO> listReviews(String Id);
    public ReviewDTO saveReview(ReviewDTO reviewDTO);
    public double listAverageRating(String Id);
}
