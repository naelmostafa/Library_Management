package com.nael.library_management.service;

import com.nael.library_management.dto.BookDTO;
import com.nael.library_management.entity.Author;
import com.nael.library_management.entity.Book;
import com.nael.library_management.repository.AuthorRepository;
import com.nael.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        return convertToDto(bookRepository.findById(id).orElse(null));
    }

    public BookDTO saveBook(BookDTO bookDto) {
        Book book = convertToEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDTO updateBook(BookDTO bookDto) {
        Book book = convertToEntity(bookDto);
        Book updatedBook = bookRepository.save(book);
        return convertToDto(updatedBook);
    }

    public BookDTO assignBookToAuthor(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            return convertToDto(bookRepository.save(book));
        }
        return null;
    }

    private BookDTO convertToDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setAuthorId(book.getAuthor().getId());
        return dto;
    }

    private Book convertToEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setAuthor(getAuthorById(dto.getAuthorId()));
        return book;
    }

    private Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
}