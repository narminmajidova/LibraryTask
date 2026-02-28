package com.example.task1.service;


import com.example.task1.model.Author;
import com.example.task1.model.Book;
import com.example.task1.repository.AuthorRepository;
import com.example.task1.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void addBook(Long authorId, String title) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found!"));

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        author.getWrittenBooks().add(book);
        authorRepository.save(author);
    }

    public List<Book> getAuthorBooks(Long authorId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found!"));

        return author.getWrittenBooks();
    }
}
