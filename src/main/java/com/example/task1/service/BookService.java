package com.example.task1.service;

import com.example.task1.dto.BookDto;
import com.example.task1.model.Author;
import com.example.task1.model.Book;
import com.example.task1.repository.AuthorRepository;
import com.example.task1.repository.BookRepository;
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

    public Book createBook(Book book, Long authorId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow();

        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public BookDto mapToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setYear(book.getYear());
        return dto;
    }

    public List<BookDto> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return mapToDto(book);
    }

    public BookDto create(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setYear(dto.getYear());

        Book saved = bookRepository.save(book);
        return mapToDto(saved);
    }

    public BookDto update(Long id, BookDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setYear(dto.getYear());

        Book updated = bookRepository.save(book);
        return mapToDto(updated);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}