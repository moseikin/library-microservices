package com.example.book.service;

import com.example.book.domain.Author;
import com.example.book.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Author getAuthorById(UUID authorId) {
        return authorRepository.findById(authorId).orElseThrow(EntityNotFoundException::new);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
