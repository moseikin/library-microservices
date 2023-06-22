package com.example.comment.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CommentRequestDto {
    private final UUID commentId;

    private final UUID bookId;

    private final String text;
}
