package com.bookstore.authordetail.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "authors")
@NoArgsConstructor
@Setter
@Getter
    public class Author{

    @Id
    String id;

    @Indexed(unique = true)
    String author;
    String bestseller;
    String bio;

}