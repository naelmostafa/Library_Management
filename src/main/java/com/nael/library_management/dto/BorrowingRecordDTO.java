package com.nael.library_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingRecordDTO {
    private Long id;
    private Long patronId;
    private Long bookId;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}