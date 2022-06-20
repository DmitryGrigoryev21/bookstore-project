package com.bookstore.bookrecommendation.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@Getter
@Setter
public class TagDTO extends RepresentationModel<TagDTO> {

    public String bookUUID;
    public String tag;
}
