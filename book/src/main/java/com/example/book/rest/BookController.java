package com.example.book.rest;

import com.example.book.domain.Book;
import com.example.book.dto.response.BookResponseDto;
import com.example.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/all")
    public List<BookResponseDto> getAll() throws ExecutionException, InterruptedException {
        return bookService.getAllBooks().get();
    }

    @GetMapping("/{bookId}")
    public BookResponseDto getById(@PathVariable UUID bookId) {
        return bookService.getBook(bookId);
    }
}
