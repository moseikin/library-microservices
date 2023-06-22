package com.example.book.service;

import com.example.book.domain.Genre;
import com.example.book.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public Genre getGenreById(UUID genreId) {
        return genreRepository.findById(genreId).orElseThrow(EntityNotFoundException::new);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
