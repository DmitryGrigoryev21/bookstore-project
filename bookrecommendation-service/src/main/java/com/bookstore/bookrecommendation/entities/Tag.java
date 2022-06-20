package com.bookstore.bookrecommendation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tags")
@NoArgsConstructor
@Setter
@Getter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true, name = "bookuuid")
    String bookUUID;
    String tag;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bookuuid", referencedColumnName = "bookuuid", nullable = false)
//    private Book book;
}
