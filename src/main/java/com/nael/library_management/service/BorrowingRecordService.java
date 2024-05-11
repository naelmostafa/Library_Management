package com.nael.library_management.service;

import com.nael.library_management.dto.BorrowingRecordDTO;
import com.nael.library_management.entity.Book;
import com.nael.library_management.entity.BorrowingRecord;
import com.nael.library_management.entity.Patron;
import com.nael.library_management.repository.BookRepository;
import com.nael.library_management.repository.BorrowingRecordRepository;
import com.nael.library_management.repository.PatronRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordService {
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public List<BorrowingRecordDTO> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BorrowingRecordDTO getBorrowingRecordById(Long id) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(id).orElse(null);
        return borrowingRecord != null ? convertToDto(borrowingRecord) : null;
    }

    public BorrowingRecordDTO rentBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new RuntimeException("Patron not found"));

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now());

        BorrowingRecord savedBorrowingRecord = borrowingRecordRepository.save(borrowingRecord);

        return convertToDto(savedBorrowingRecord);
    }

    public BorrowingRecordDTO returnBook(Long bookId, Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        borrowingRecord.setReturnDate(LocalDate.now());
        BorrowingRecord savedBorrowingRecord = borrowingRecordRepository.save(borrowingRecord);
        return convertToDto(savedBorrowingRecord);
    }

    private BorrowingRecordDTO convertToDto(BorrowingRecord borrowingRecord) {
        BorrowingRecordDTO dto = new BorrowingRecordDTO();
        dto.setId(borrowingRecord.getId());
        dto.setBorrowingDate(borrowingRecord.getBorrowingDate());
        dto.setReturnDate(borrowingRecord.getReturnDate());
        dto.setBookId(borrowingRecord.getBook().getId());
        dto.setPatronId(borrowingRecord.getPatron().getId());
        return dto;
    }


}