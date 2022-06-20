package com.grigoryev.bookstore.mappers;

import com.grigoryev.bookstore.controllers.BookShortResponseDTO;
import com.grigoryev.bookstore.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookShortResponseMapper {
    BookShortResponseDTO entityToModel(Book book);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "summary", ignore = true),
            @Mapping(target = "isbn", ignore = true),
    })
    Book modelToEntity(BookShortResponseDTO bookShortResponseDTO);

    List<BookShortResponseDTO> entityListToResponseModelList(List<Book> books);
}
