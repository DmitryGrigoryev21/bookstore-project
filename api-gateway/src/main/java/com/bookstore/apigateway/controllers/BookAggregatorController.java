package com.bookstore.apigateway.controllers;

import com.bookstore.apigateway.aggregates.BookAggregate;
import com.bookstore.apigateway.services.BookAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookAggregatorController {

    @Autowired
    private BookAggregateService bookAggregateService;

    public BookAggregatorController(BookAggregateService bookAggregateService){
        this.bookAggregateService = bookAggregateService;
    }

    @GetMapping(
            value = "/book/{Id}",
            produces = "application/json"
    )
    public ResponseEntity<BookAggregateDTO> getBookAggregate(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listBookByBookUUID(Id));
    }

    @GetMapping(
            value = "/books",
            produces = "application/json"
    )
    public ResponseEntity<List<BookAggregateDTO>> getBookAggregates(){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listBookAggregates());
    }
    @PostMapping(
            value = "/book",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<BookAggregateDTO> setBookAggregate(@RequestBody BookAggregateDTO bookAggregateDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.saveBookAggregate(bookAggregateDTO));
    }
    @DeleteMapping(
            value = "/book/{Id}",
            produces = "application/json"
    )
    public ResponseEntity<BookAggregateDTO> deleteBookAggregate(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.deleteBookAggregate(Id));
    }
    @PutMapping(
            value = "/book/{Id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<BookAggregateDTO> setBookAggregate(@RequestBody BookAggregateDTO bookAggregateDTO, @PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.updateBookAggregate(bookAggregateDTO, Id));
    }

    //--book--
    //get
    @GetMapping(
            value = "/book/{Id}/book",
            produces = "application/json"
    )
    public ResponseEntity<BookDetailDTO> getBook(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listBookOnlyByBookUUID(Id));
    }
    //get all
    @GetMapping(
            value = "/books/book",
            produces = "application/json"
    )
    public ResponseEntity<List<BookDetailDTO>> getBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listBooks());
    }
    //post
    @PostMapping(
            value = "/book/book",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<BookDetailDTO> setBook(@RequestBody BookDetailDTO bookDetailDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.saveBook(bookDetailDTO));
    }
    //delete
    @DeleteMapping(
            value = "/book/{Id}/book",
            produces = "application/json"
    )
    public ResponseEntity<BookDetailDTO> deleteBook(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.deleteBook(Id));
    }
    //put
    @PutMapping(
            value = "/book/{Id}/book",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<BookDetailDTO> setBook(@RequestBody BookDetailDTO bookDetailDTO, @PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.updateBook(bookDetailDTO, Id));
    }

    //--author--
    //get all
    @GetMapping(
            value = "/book/authors",
            produces = "application/json"
    )
    public ResponseEntity<List<AuthorDetailDTO>> getAllAuthors(){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listAllAuthors());
    }
    //get
    @GetMapping(
            value = "/book/{Id}/author",
            produces = "application/json"
    )
    public ResponseEntity<AuthorDetailDTO> getAuthor(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listAuthor(Id));
    }
    //post
    @PostMapping(
            value = "/book/author",
            consumes = "application/json",
            produces = "application/json"
    )

    public ResponseEntity<AuthorDetailDTO> setAuthor(@RequestBody AuthorDetailDTO authorDetailDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.saveAuthor(authorDetailDTO));
    }
    //delete
    @DeleteMapping(
            value = "/book/{Id}/author",
            produces = "application/json"
    )
    public ResponseEntity<AuthorDetailDTO> deleteAuthor(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.deleteAuthor(Id));
    }
    //put
    @PutMapping(
            value = "/book/{Id}/author",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<AuthorDetailDTO> setBook(@RequestBody AuthorDetailDTO authorDetailDTO, @PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.updateAuthor(authorDetailDTO, Id));
    }

    //--recommendation--
    //get all
    @GetMapping(
            value = "/book/tags",
            produces = "application/json"
    )
    public ResponseEntity<List<TagDTO>> getAllTags(){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listAllTags());
    }
    //get
    @GetMapping(
            value = "/book/{Id}/tags",
            produces = "application/json"
    )

    public ResponseEntity<List<TagDTO>> getTags(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listTags(Id));
    }
    //post
    @PostMapping(
            value = "/book/tag",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<TagDTO> setBook(@RequestBody TagDTO tagDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.saveTag(tagDTO));
    }
    //get unique
    @GetMapping(
            value = "/book/tags/unique",
            produces = "application/json"
    )
    public ResponseEntity<List<String>> getUniqueTags(){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listUniqueTags());
    }
    //get recommended
    @GetMapping(
            value = "/book/{Id}/tags/recommended",
            produces = "application/json"
    )
    public ResponseEntity<List<String>> getRecommended(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listRecommended(Id));
    }

    //--review--
    //get all
    @GetMapping(
            value = "/book/reviews",
            produces = "application/json"
    )
    public ResponseEntity<List<ReviewDTO>> getAllReviews(){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listAllReviews());
    }
    //get
    @GetMapping(
            value = "/book/{Id}/reviews",
            produces = "application/json"
    )
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listReviews(Id));
    }
    //post
    @PostMapping(
            value = "/book/review",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<ReviewDTO> setReview(@RequestBody ReviewDTO reviewDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.saveReview(reviewDTO));
    }
    //get average
    @GetMapping(
            value = "/book/{Id}/review/average",
            produces = "application/json"
    )
    public ResponseEntity<Double> getAverage(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(bookAggregateService.listAverageRating(Id));
    }
}
