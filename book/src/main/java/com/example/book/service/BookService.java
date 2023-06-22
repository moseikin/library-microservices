package com.example.book.service;

import com.example.book.domain.Author;
import com.example.book.domain.Book;
import com.example.book.domain.Genre;
import com.example.book.dto.request.BookRequestDto;
import com.example.book.dto.response.BookResponseDto;
import com.example.book.mapper.BookMapper;
import com.example.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private static final String NEW_BOOK_WITH_ID = "The new book must not have an ID";
    private static final String UPDATE_BOOK_WITHOUT_ID = "No id for book being updated";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookMapper bookMapper;

    @Transactional
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        if (bookRequestDto.getBookId() == null) {
            Author author = authorService.getAuthorById(bookRequestDto.getAuthorId());

            Genre genre = genreService.getGenreById(bookRequestDto.getGenreId());

            Book book = new Book(null, bookRequestDto.getBookName(), author, genre);

            Book bookSaved = bookRepository.save(book);

            return bookMapper.toResponseDto(bookSaved);
        } else {

            throw new IllegalStateException(NEW_BOOK_WITH_ID);
        }
    }

    @Transactional(readOnly = true)
    public BookResponseDto getBook(UUID bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);

        return bookMapper.toResponseDto(book);
    }

    @Transactional
    public BookResponseDto updateBook(BookRequestDto bookRequestDto) {
        if (bookRequestDto.getBookId() == null) {

            throw new IllegalStateException(UPDATE_BOOK_WITHOUT_ID);
        } else {
            Author author = authorService.getAuthorById(bookRequestDto.getAuthorId());

            Genre genre = genreService.getGenreById(bookRequestDto.getGenreId());

            Book book = bookRepository.save(new Book(bookRequestDto.getBookId(), bookRequestDto.getBookName(),
                    author, genre));

            return bookMapper.toResponseDto(book);
        }
    }

    @Transactional
    public void deleteBook(UUID bookId) {
        bookRepository.deleteById(bookId);
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(bookMapper::toResponseDto).collect(Collectors.toList());
    }
}
