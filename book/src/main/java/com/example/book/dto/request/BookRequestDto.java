package com.example.book.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private UUID bookId;

    @NotNull(message = "Название книги не должно быть пустым")
    @Size(min = 1, max = 100, message = "Длина названия книги должна быть в пределах от {min} до {max} символов")
    private String bookName;

    @Min(1)
    @Max(Long.MAX_VALUE)
    private UUID authorId;

    @Min(1)
    @Max(Long.MAX_VALUE)
    private UUID genreId;
}
