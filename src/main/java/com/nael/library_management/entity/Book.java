package com.nael.library_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    @JoinColumn(name = "authorId")
    @ManyToOne
    private Author author;
    private int publicationYear;
}
