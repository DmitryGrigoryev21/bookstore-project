package com.grigoryev.bookstore.mappers;


import com.grigoryev.bookstore.controllers.BookLongResponseDTO;

import com.grigoryev.bookstore.entities.Book;
import org.mapstruct.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookLongResponseMapper {
    BookLongResponseDTO entityToModel(Book book);
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Book modelToEntity(BookLongResponseDTO bookLongResponseDTO);

    List<BookLongResponseDTO> entityListToResponseModelList(List<Book> books);

//    @AfterMapping
//    default void addLinks(@MappingTarget BookLongResponseDTO model, Book entity) {
//        Link selfLink = WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder
//                        .methodOn(BookController.class)
//                        .listBookByBookUUID(model.getBookUUID()))
//                .withSelfRel();
//        model.add(selfLink);
//
//        Link reviewLink = WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder
//                        .methodOn(ReviewController.class)
//                        .listReviewsByBookUUID(entity.getBookUUID()))
//                .withRel("reviews");
//        model.add(reviewLink);
//
//        Link tagLink = WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder
//                        .methodOn(TagController.class)
//                        .listTagsByBookUUID(entity.getBookUUID()))
//                .withRel("tags");
//        model.add(tagLink);
//    }
}
