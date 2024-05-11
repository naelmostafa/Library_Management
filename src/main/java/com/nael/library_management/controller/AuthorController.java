package com.nael.library_management.controller;

import com.nael.library_management.dto.AuthorDTO;
import com.nael.library_management.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Author Management")
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ApiOperation(value = "View a list of all authors", response = List.class)
    @GetMapping("/all")
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @ApiOperation(value = "Add an author", response = AuthorDTO.class)
    @PostMapping("/add")
    public AuthorDTO addAuthor(@RequestBody AuthorDTO author) {
        return authorService.saveAuthor(author);
    }

    @ApiOperation(value = "Delete an author")
    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @ApiOperation(value = "Update an author", response = AuthorDTO.class)
    @PutMapping("/update")
    public AuthorDTO updateAuthor(@RequestBody AuthorDTO author) {
        return authorService.updateAuthor(author);
    }
}