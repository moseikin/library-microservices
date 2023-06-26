package com.example.book.repository;

import com.example.book.domain.Book;
import com.example.book.dto.response.BookResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("select new com.example.book.dto.response.BookResponseDto(" +
            "b.bookId, " +
            "b.bookName, " +
            "a.name, " +
            "a.surname, " +
            "g.name) " +
            "from Book b " +
            "left join Author a on b.author.authorId = a.authorId " +
            "left join Genre g on b.genre.id = g.id")
    public List<BookResponseDto> findAllInitialized();
}
