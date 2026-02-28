package com.example.task1.controller;


import com.example.task1.model.Author;
import com.example.task1.model.Book;
import com.example.task1.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @PostMapping("/{id}/books")
    public void addBook(@PathVariable Long id,
                        @RequestParam String title) {
        authorService.addBook(id, title);
    }

    @GetMapping("/{id}/books")
    public List<Book> getBooks(@PathVariable Long id) {
        return authorService.getAuthorBooks(id);
    }
}