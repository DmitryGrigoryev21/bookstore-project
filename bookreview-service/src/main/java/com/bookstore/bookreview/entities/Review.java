package com.bookstore.bookreview.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@Setter
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true, name = "bookuuid")
    String bookUUID;
    String review;
    int rating;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bookuuid", referencedColumnName = "bookuuid", nullable = false)
//    private Book book;
}
