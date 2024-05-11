package com.nael.library_management.controller;

import com.nael.library_management.dto.BookDTO;
import com.nael.library_management.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/add")
    public BookDTO addBook(@RequestBody BookDTO book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/update")
    public BookDTO updateBook(@RequestBody BookDTO book) {
        return bookService.updateBook(book);
    }

    @PutMapping("/assign/{bookId}/{authorId}")
    public BookDTO assignBookToAuthor(@PathVariable Long bookId, @PathVariable Long authorId) {
        return bookService.assignBookToAuthor(bookId, authorId);
    }

}