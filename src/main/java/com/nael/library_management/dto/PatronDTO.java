package com.nael.library_management.dto;

import lombok.Data;

@Data
public class PatronDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}