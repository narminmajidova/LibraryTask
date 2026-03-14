package com.example.task1.client;

import com.example.task1.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name="book-service", url="http://localhost:8081")
public interface BookClient {

    @GetMapping("/api/books")
    List<BookDto> getAllBooks();

    @GetMapping("/api/books/{id}")
    BookDto getBookById(@PathVariable("id") Long id);
}