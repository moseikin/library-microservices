package com.example.comment.mapper;

import com.example.comment.domain.Comment;
import com.example.comment.dto.response.CommentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommentMapper extends GenericMapper<Comment, CommentResponseDto> {

    @Override
    @Mapping(source = "id", target = "commentId")
    CommentResponseDto toResponseDto(Comment entity);
}
