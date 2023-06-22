package com.example.comment.mapper;

public interface GenericMapper<E, R> {

    R toResponseDto(E entity);
}
