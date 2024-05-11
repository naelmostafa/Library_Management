package com.nael.library_management.controller;

import com.nael.library_management.dto.BorrowingRecordDTO;
import com.nael.library_management.service.BorrowingRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowing-records")
public class BorrowingRecordController {
    private final BorrowingRecordService borrowingRecordService;

    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @GetMapping("/all")
    public List<BorrowingRecordDTO> getAllBorrowingRecords() {
        return borrowingRecordService.getAllBorrowingRecords();
    }

    @GetMapping("/{id}")
    public BorrowingRecordDTO getBorrowingRecordById(@PathVariable Long id) {
        return borrowingRecordService.getBorrowingRecordById(id);
    }

    @PostMapping("/rent/{bookId}/patron/{patronId}")
    public BorrowingRecordDTO rentBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return borrowingRecordService.rentBook(bookId, patronId);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public BorrowingRecordDTO returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return borrowingRecordService.returnBook(bookId, patronId);
    }
}