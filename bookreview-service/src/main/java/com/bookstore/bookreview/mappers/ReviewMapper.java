package com.bookstore.bookreview.mappers;

import com.bookstore.bookreview.controllers.ReviewController;
import com.bookstore.bookreview.controllers.ReviewDTO;

import com.bookstore.bookreview.entities.Review;
import org.mapstruct.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
//    @Mappings({
//            @Mapping(expression = "java( review.getBook().getBookUUID())", target = "bookUUID"),
//    })
    ReviewDTO entityToModel(Review review);
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Review modelToEntity(ReviewDTO reviewDTO);

    List<ReviewDTO> entityListToResponseModelList(List<Review> reviews);

//    @AfterMapping
//    default void addLinks(@MappingTarget ReviewDTO model, Review entity) {
//        Link selfLink = WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder
//                        .methodOn(ReviewController.class)
//                        .listReviewsByBookUUID(model.getBookUUID()))
//                .withSelfRel();
//        model.add(selfLink);
//
//        Link bookLink = WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder
//                        .methodOn(BookController.class)
//                        .listBookByBookUUID(entity.getBook().getBookUUID()))
//                .withRel("book");
//        model.add(bookLink);
//    }
}
