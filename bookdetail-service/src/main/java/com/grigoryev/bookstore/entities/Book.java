package com.grigoryev.bookstore.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Setter
@Getter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NaturalId
    @Column(unique = true, name = "bookuuid")
    String bookUUID;

    String title;
    String author;
    String summary;
    String isbn;
    double price;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
//    private Set<Tag> tags = new HashSet<Tag>(0);
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
//    private Set<Review> reviews = new HashSet<Review>(0);

}

