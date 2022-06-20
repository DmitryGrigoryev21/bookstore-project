package com.bookstore.authordetail.mappers;

import com.bookstore.authordetail.controllers.AuthorDTO;
import com.bookstore.authordetail.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO entityToModel(Author author);
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Author modelToEntity(AuthorDTO authorDTO);

    List<AuthorDTO> entityListToResponseModelList(List<Author> authors);
}
