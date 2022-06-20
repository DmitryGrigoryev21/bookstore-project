package com.bookstore.apigateway.mappers;

import com.bookstore.apigateway.aggregates.BookAggregate;
import com.bookstore.apigateway.controllers.AuthorDetailDTO;
import com.bookstore.apigateway.controllers.BookAggregateDTO;
import com.bookstore.apigateway.controllers.BookAggregatorController;
import com.bookstore.apigateway.controllers.BookDetailDTO;
import org.mapstruct.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Mapper(componentModel = "spring")
public interface BookAggregateMapper {

    BookAggregateDTO entityToModel(BookAggregate bookAggregate);
    @Mappings({
            @Mapping(target = "id", ignore = true),
//            @Mapping(target = "authorDetail", ignore = true),
//            @Mapping(target = "tag", ignore = true),
//            @Mapping(target = "review", ignore = true),
    })
    BookAggregate modelToEntity(BookAggregateDTO bookAggregateDTO);

    BookAggregate clientModelsToAggregate(BookAggregateDTO bookAggregateDTO);

    @AfterMapping
    default void addLinks(@MappingTarget BookAggregateDTO model, BookAggregate entity) {
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookAggregatorController.class)
                        .getBookAggregate(model.getBookUUID()))
                .withSelfRel();
        model.add(selfLink);

        Link reviewLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookAggregatorController.class)
                        .getReviews(entity.getBookUUID()))
                .withRel("reviews");
        model.add(reviewLink);

        Link tagLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookAggregatorController.class)
                        .getTags(entity.getBookUUID()))
                .withRel("tags");
        model.add(tagLink);
    }
}
