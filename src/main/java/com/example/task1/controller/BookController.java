package com.example.task1.controller;

import com.example.task1.dto.BookDto;
import com.example.task1.model.Book;
import com.example.task1.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasAuthority('BOOK_READ')")
    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    @PreAuthorize("hasAuthority('BOOK_READ')")
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PreAuthorize("hasAuthority('BOOK_CREATE')")
    @PostMapping("/{authorId}")
    public Book createBook(@RequestBody Book book,
                           @PathVariable Long authorId) {
        return bookService.createBook(book, authorId);
    }

    @PreAuthorize("hasAuthority('BOOK_CREATE')")
    @PostMapping
    public BookDto create(@RequestBody BookDto dto) {
        return bookService.create(dto);
    }

    @PreAuthorize("hasAuthority('BOOK_UPDATE')")
    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id,
                              @RequestBody BookDto dto) {
        return bookService.update(id, dto);
    }

    @PreAuthorize("hasAuthority('BOOK_DELETE')")
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "Book deleted successfully";
    }
}