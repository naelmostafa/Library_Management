package com.nael.library_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patronId")
    private Patron patron;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    private LocalDate borrowingDate;
    private LocalDate returnDate;

}
