package com.example.comment.service;

import com.example.comment.domain.Comment;
import com.example.comment.dto.request.CommentRequestDto;
import com.example.comment.dto.response.CommentResponseDto;
import com.example.comment.mapper.CommentMapper;
import com.example.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private static final String NEW_COMMENT_WITH_ID = "The new comment must not have an ID";
    private static final String UPDATE_COMMENT_WITHOUT_ID = "No id for comment being updated";

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Comment comment = createCommentEntity(commentRequestDto);

        if (commentRequestDto.getCommentId() == null) {

            commentRepository.save(comment);

            return commentMapper.toResponseDto(comment);
        } else {

            throw new IllegalStateException(NEW_COMMENT_WITH_ID);
        }

    }

    @Transactional(readOnly = true)
    public CommentResponseDto getComment(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return commentMapper.toResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto) {
        if (commentRequestDto.getCommentId() == null) {

            throw new IllegalStateException(UPDATE_COMMENT_WITHOUT_ID);
        } else {
            Comment comment = createCommentEntity(commentRequestDto);

            comment = commentRepository.save(comment);

            return commentMapper.toResponseDto(comment);
        }
    }

    // toDo в маппер
    private Comment createCommentEntity(CommentRequestDto commentRequestDto) {
        return new Comment(commentRequestDto.getCommentId(), commentRequestDto.getBookId(), commentRequestDto.getText());
    }

    @Transactional
    public void deleteComment(long id) {

        commentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getAllCommentsByBookId(UUID bookId) {
        List<Comment> comments = commentRepository.getAllCommentsByBookId(bookId);

        return comments.stream()
                .map(commentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
