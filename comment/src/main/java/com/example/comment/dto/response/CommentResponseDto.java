package com.example.comment.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {

    private final UUID commentId;

    private final String text;
}
