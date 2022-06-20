package com.bookstore.apigateway.services;

import com.bookstore.apigateway.aggregates.BookAggregate;
import com.bookstore.apigateway.controllers.*;
import com.bookstore.apigateway.mappers.BookAggregateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookAggregateServiceImpl implements BookAggregateService {

    @Autowired
    private BookAggregateMapper bookAggregateMapper;
    //private final AuthorMapper authorMapper;
    private final BookDetailServiceClient bookDetailServiceClient;
    private final AuthorDetailServiceClient authorDetailServiceClient;
    private final ReviewServiceClient reviewServiceClient;
    private final TagServiceClient tagServiceClient;

    @Autowired
    public BookAggregateServiceImpl(BookAggregateMapper bookAggregateMapper,
                                    BookDetailServiceClient bookDetailServiceClient,
                                    AuthorDetailServiceClient authorDetailServiceClient,
                                    ReviewServiceClient reviewServiceClient,
                                    TagServiceClient tagServiceClient) {
        this.bookAggregateMapper = bookAggregateMapper;
        //this.authorMapper = authorMapper;
        this.bookDetailServiceClient = bookDetailServiceClient;
        this.authorDetailServiceClient = authorDetailServiceClient;
        this.reviewServiceClient = reviewServiceClient;
        this.tagServiceClient = tagServiceClient;
    }

    //book aggregate
    @Override
    public BookAggregateDTO listBookByBookUUID(String Id) {
        BookAggregateDTO bookAggregateDTO = bookDetailServiceClient.getBookDetail(Id);
        AuthorDetailDTO authorDetailDTO = authorDetailServiceClient.getAuthorDetail(bookAggregateDTO.getAuthor());
        List<ReviewDTO> reviewDTOS = reviewServiceClient.getReviews(Id);
        List<TagDTO> tagDTOS = tagServiceClient.getTags(Id);
        //BookAggregate bookAggregate = bookAggregateMapper.modelToEntity(bookAggregateDTO);
        bookAggregateDTO.setAuthorDetail(authorDetailDTO);
        bookAggregateDTO.setReview(reviewDTOS);
        bookAggregateDTO.setTag(tagDTOS);
        return bookAggregateDTO;
    }

    @Override
    public List<BookAggregateDTO> listBookAggregates() {
        List<BookAggregateDTO> bookAggregateDTO = bookDetailServiceClient.getAllBookDetail();
        for(BookAggregateDTO x : bookAggregateDTO){
            AuthorDetailDTO authorDetailDTO = authorDetailServiceClient.getAuthorDetail(x.getAuthor());
            List<ReviewDTO> reviewDTOS = reviewServiceClient.getReviews(x.getBookUUID());
            List<TagDTO> tagDTOS = tagServiceClient.getTags(x.getBookUUID());
            x.setAuthorDetail(authorDetailDTO);
            x.setReview(reviewDTOS);
            x.setTag(tagDTOS);
        }
        return bookAggregateDTO;
    }
    @Override
    public BookAggregateDTO saveBookAggregate(BookAggregateDTO bookAggregateDTO){
        BookDetailDTO bookDetailDTO = new BookDetailDTO();
        bookDetailDTO.setAuthor(bookAggregateDTO.getAuthor());
        bookDetailDTO.setTitle(bookAggregateDTO.getTitle());
        bookDetailDTO.setSummary(bookAggregateDTO.getSummary());
        bookDetailDTO.setIsbn(bookAggregateDTO.getIsbn());
        bookDetailDTO.setPrice(bookAggregateDTO.getPrice());
        BookDetailDTO newBookDetailDTO = bookDetailServiceClient.setBook(bookDetailDTO);
        String newUUID = null;
        if (newBookDetailDTO != null)
            newUUID = newBookDetailDTO.getBookUUID();
        authorDetailServiceClient.setAuthor(bookAggregateDTO.getAuthorDetail());
        List<ReviewDTO> reviewDTO = bookAggregateDTO.getReview();
        //List<ReviewDTO> newReviewDTOs = new ArrayList<>();
        if (reviewDTO != null && reviewDTO.size() > 0) {
            for (ReviewDTO x : reviewDTO) {
                if (x.getBookUUID() == null)
                    x.setBookUUID(newUUID);
                reviewServiceClient.setReview(x);
                //newReviewDTOs.add(reviewDTO1);
            }
        }
        List<TagDTO> tagDTOS = bookAggregateDTO.getTag();
        //List<TagDTO> newTagDTOs = new ArrayList<>();
        if (tagDTOS != null && tagDTOS.size() > 0) {
            for (TagDTO x : tagDTOS) {
                if (x.getBookUUID() == null)
                    x.setBookUUID(newUUID);
                tagServiceClient.setTag(x);
                //newTagDTOs.add(tagDTO);
            }
        }
        BookAggregateDTO newBookAggregateDTO = bookDetailServiceClient.getBookDetail(bookAggregateDTO.getBookUUID());
        return newBookAggregateDTO;
    }
    @Override
    public BookAggregateDTO deleteBookAggregate(String Id){
        BookAggregateDTO bookAggregateDTO = bookDetailServiceClient.getBookDetail(Id);
        bookDetailServiceClient.deleteBook(Id);
        reviewServiceClient.deleteReview(Id);
        tagServiceClient.deleteTag(Id);
        authorDetailServiceClient.deleteAuthor(bookAggregateDTO.getAuthor());
        return  bookAggregateDTO;
    }
    @Override
    public BookAggregateDTO updateBookAggregate(BookAggregateDTO bookAggregateDTO, String Id){
        BookDetailDTO bookDetailDTO = new BookDetailDTO();
        bookDetailDTO.setAuthor(bookAggregateDTO.getAuthor());
        bookDetailDTO.setTitle(bookAggregateDTO.getTitle());
        bookDetailDTO.setSummary(bookAggregateDTO.getSummary());
        bookDetailDTO.setIsbn(bookAggregateDTO.getIsbn());
        bookDetailDTO.setPrice(bookAggregateDTO.getPrice());
        //BookDetailDTO newBookDetailDTO = bookDetailServiceClient.setBook(bookDetailDTO);
        String newUUID = null;
        if (bookDetailDTO.getBookUUID() != null)
            newUUID = bookAggregateDTO.getBookUUID();
        AuthorDetailDTO newAuthorDetailDTO = bookAggregateDTO.getAuthorDetail();
        List<ReviewDTO> reviewDTO = bookAggregateDTO.getReview();
        List<ReviewDTO> newReviewDTOs = new ArrayList<>();
        if (reviewDTO != null && reviewDTO.size() > 0) {
            for (ReviewDTO x : reviewDTO) {
                if (x.getBookUUID() == null)
                    x.setBookUUID(newUUID);
                newReviewDTOs.add(x);
            }
        }
        List<TagDTO> tagDTOS = bookAggregateDTO.getTag();
        List<TagDTO> newTagDTOs = new ArrayList<>();
        if (tagDTOS != null && tagDTOS.size() > 0) {
            for (TagDTO x : tagDTOS) {
                if (x.getBookUUID() == null)
                    x.setBookUUID(newUUID);
                newTagDTOs.add(x);
            }
        }
        BookAggregateDTO newBookAggregateDTO = new BookAggregateDTO();
        newBookAggregateDTO.setTag(newTagDTOs);
        newBookAggregateDTO.setAuthorDetail(newAuthorDetailDTO);
        newBookAggregateDTO.setReview(newReviewDTOs);
        newBookAggregateDTO.setAuthor(bookAggregateDTO.getAuthor());
        newBookAggregateDTO.setTitle(bookAggregateDTO.getTitle());
        newBookAggregateDTO.setSummary(bookAggregateDTO.getSummary());
        newBookAggregateDTO.setIsbn(bookAggregateDTO.getIsbn());
        newBookAggregateDTO.setPrice(bookAggregateDTO.getPrice());
        bookDetailServiceClient.updateBook(bookDetailDTO, Id);
        authorDetailServiceClient.updateAuthor(newAuthorDetailDTO, bookDetailDTO.getAuthor());
        for (ReviewDTO x : newReviewDTOs)
            reviewServiceClient.updateReview(x, Id);
        for (TagDTO x : newTagDTOs)
            tagServiceClient.updateTag(x, Id);

        return newBookAggregateDTO;
    }

    //book
    @Override
    public BookDetailDTO listBookOnlyByBookUUID(String Id){
        BookDetailDTO bookDetailDTO = bookDetailServiceClient.getBookOnlyDetail(Id);
        if (bookDetailDTO == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return bookDetailDTO;
    }
    @Override
    public List<BookDetailDTO> listBooks(){
        return bookDetailServiceClient.getAllBooks();
    }
    @Override
    public BookDetailDTO saveBook(BookDetailDTO bookDetailDTO){
        return bookDetailServiceClient.setBook(bookDetailDTO);
    }
    @Override
    public BookDetailDTO deleteBook(String Id){
        return bookDetailServiceClient.deleteBook(Id);
    }
    @Override
    public BookDetailDTO updateBook(BookDetailDTO bookDetailDTO, String Id){
        return bookDetailServiceClient.updateBook(bookDetailDTO, Id);
    }

    //author
    @Override
    public List<AuthorDetailDTO> listAllAuthors(){
        return authorDetailServiceClient.getAllAuthors();
    }
    @Override
    public AuthorDetailDTO listAuthor(String Id){
        return authorDetailServiceClient.getAuthorDetail(Id);
    }
    @Override
    public AuthorDetailDTO saveAuthor(AuthorDetailDTO authorDetailDTO){
        return authorDetailServiceClient.setAuthor(authorDetailDTO);
    }
    @Override
    public AuthorDetailDTO deleteAuthor(String Id){
        return authorDetailServiceClient.deleteAuthor(Id);
    }
    @Override
    public AuthorDetailDTO updateAuthor(AuthorDetailDTO authorDetailDTO, String Id) {
        return authorDetailServiceClient.updateAuthor(authorDetailDTO, Id);
    }

    //recommendation
    @Override
    public List<TagDTO> listAllTags(){
        return tagServiceClient.getAllTags();
    }
    @Override
    public List<TagDTO> listTags(String Id){
        return tagServiceClient.getTags(Id);
    }
    @Override
    public TagDTO saveTag(TagDTO tagDTO){
        return tagServiceClient.setTag(tagDTO);
    }
    @Override
    public List<String> listUniqueTags(){
        return tagServiceClient.getUniqueTags();
    }
    @Override
    public List<String> listRecommended(String Id){
        return tagServiceClient.getRecommended(Id);
    }

    //review
    @Override
    public List<ReviewDTO> listAllReviews(){
        return reviewServiceClient.getAllReviews();
    }
    @Override
    public List<ReviewDTO> listReviews(String Id){
        return reviewServiceClient.getReviews(Id);
    }
    @Override
    public ReviewDTO saveReview(ReviewDTO reviewDTO){
        return reviewServiceClient.setReview(reviewDTO);
    }
    @Override
    public double listAverageRating(String Id){
        return reviewServiceClient.getAverageRating(Id);
    }
}
