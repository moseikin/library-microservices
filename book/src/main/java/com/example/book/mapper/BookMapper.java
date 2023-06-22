package com.example.book.mapper;

import com.example.book.domain.Book;
import com.example.book.dto.response.BookResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper extends GenericMapper<Book, BookResponseDto> {

    @Override
    @Mapping(source = "book.author.name", target = "authorName")
    @Mapping(source = "book.author.surname", target = "authorSurname")
    @Mapping(source = "book.genre.name", target = "genreName")
    BookResponseDto toResponseDto(Book book);
}
