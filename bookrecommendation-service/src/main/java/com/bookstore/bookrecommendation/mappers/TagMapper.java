package com.bookstore.bookrecommendation.mappers;

import com.bookstore.bookrecommendation.controllers.TagDTO;

import com.bookstore.bookrecommendation.entities.Tag;
import org.mapstruct.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
//    @Mappings({
//            @Mapping(expression = "java( tag.getBook().getBookUUID())", target = "bookUUID"),
//    })
    TagDTO entityToModel(Tag tag);
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Tag modelToEntity(TagDTO tagDTO);

    List<TagDTO> entityListToResponseModelList(List<Tag> tags);

//    @AfterMapping
//    default void addLinks(@MappingTarget TagDTO model, Tag entity) {
//        Link selfLink = WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder
//                        .methodOn(TagController.class)
//                        .listTagsByBookUUID(model.getBookUUID()))
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
