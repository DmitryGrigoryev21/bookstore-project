package com.bookstore.apigateway.mappers;

import com.bookstore.apigateway.aggregates.BookAggregate;
import com.bookstore.apigateway.controllers.BookAggregatorController;
import com.bookstore.apigateway.controllers.TagDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Mapper(componentModel = "spring")
public interface TagMapper {
        @AfterMapping
    default void addLinks(@MappingTarget TagDTO model, BookAggregate entity) {
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookAggregatorController.class)
                        .getTags(model.getBookUUID()))
                .withSelfRel();
        model.add(selfLink);

        Link bookLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookAggregatorController.class)
                        .getBookAggregate(entity.getBookUUID()))
                .withRel("book");
        model.add(bookLink);
    }
}
