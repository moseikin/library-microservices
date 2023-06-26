package com.example.book.service;

import com.example.book.domain.Author;
import com.example.book.domain.Book;
import com.example.book.domain.Genre;
import com.example.book.dto.request.BookRequestDto;
import com.example.book.dto.response.BookResponseDto;
import com.example.book.mapper.BookMapper;
import com.example.book.repository.BookRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Примеры работы Resilence4j: CircuitBreaker и Timeout
 * Пример работы Retry показан в ZUUL RequestService.createTokenRequest
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
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


    /**
     * Пример работы resilience4j CircuitBreaker
     * enablePause останавливает поток на величину, большую, чем определено в конфиге
     * (resilience4j.circuitbreaker.configs.default.slowCallDurationThreshold).
     * Поэтому каждый вызов считается медленным.
     * Факт медленного вызова записывается в круговой массив (slidingWindow).
     * При заполнении массива (slidingWindowSize: 3) происходит отказ сервиса и вызов метода, указанного в параметре fallbackMethod
     * После этого сервис недоступен в течение времени, казанного в параметре waitDurationInOpenState. Круговой массив при этом очищается
     */
    @CircuitBreaker(name = "default", fallbackMethod = "openedCircuitBreaker")
    public BookResponseDto getBook(UUID bookId) {
        enablePause();

        Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);

        return bookMapper.toResponseDto(book);
    }

    public BookResponseDto openedCircuitBreaker(Throwable t) {
        t.printStackTrace();
        return new BookResponseDto();
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

    /**
     * Если включить паузу в потоке, то будет вызываться метод, указанный в параметре fallbackMethod.
     * Так как пауза больше, чем параметр resilience4j.timelimiter.configs.default.timeoutDuration
     */
    @TimeLimiter(name = "default", fallbackMethod = "timeOut")
    @Bulkhead(name = "default", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<List<BookResponseDto>> getAllBooks() {
//        enablePause();

        List<BookResponseDto> books = bookRepository.findAllInitialized();

        return CompletableFuture.completedFuture(books);
    }

    private void enablePause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private CompletableFuture<List<BookResponseDto>> timeOut(Throwable t) {
        t.printStackTrace();
        return CompletableFuture.completedFuture(Collections.singletonList(new BookResponseDto()));
    }
}
