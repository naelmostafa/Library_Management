package com.nael.library_management.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private Long authorId;
    private int publicationYear;
}
