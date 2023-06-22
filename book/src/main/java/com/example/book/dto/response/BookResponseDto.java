package com.example.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private UUID bookId;

    private String bookName;

    private String authorName;

    private String authorSurname;

    private String genreName;

    public String getNameWithSurname() {
        return authorName + " " + authorSurname;
    }
}
