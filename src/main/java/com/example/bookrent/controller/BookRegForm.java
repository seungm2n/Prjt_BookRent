package com.example.bookrent.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRegForm {
    @NotEmpty(message = "도서명은 필수 입력항목입니다.")
    private String title;

    @NotEmpty(message = "저자명은 필수 입력항목입니다.")
    private String author;
}
