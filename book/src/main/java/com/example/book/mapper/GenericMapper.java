package com.example.book.mapper;

public interface GenericMapper<E, R> {

    R toResponseDto(E entity);
}
