package com.grigoryev.bookstore.mappers;

import com.grigoryev.bookstore.controllers.BookRequestDTO;
import com.grigoryev.bookstore.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookRequestMapper {

    BookRequestDTO entityToModel(Book book);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "bookUUID", ignore = true),
    })
    Book modelToEntity(BookRequestDTO bookRequestDTO);
}
