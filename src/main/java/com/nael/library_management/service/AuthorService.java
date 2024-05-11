package com.nael.library_management.service;

import com.nael.library_management.dto.AuthorDTO;
import com.nael.library_management.entity.Author;
import com.nael.library_management.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AuthorDTO saveAuthor(AuthorDTO authorDto) {
        Author author = convertToEntity(authorDto);
        Author savedAuthor = authorRepository.save(author);
        return convertToDto(savedAuthor);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public AuthorDTO updateAuthor(AuthorDTO authorDto) {
        Author author = convertToEntity(authorDto);
        Author updatedAuthor = authorRepository.save(author);
        return convertToDto(updatedAuthor);
    }


    private AuthorDTO convertToDto(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        return dto;
    }

    private Author convertToEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        return author;
    }
}