package com.example.kwordpocket.qna.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerCreateRequest {

    @NotNull(message = "작성자 ID는 필수입니다.")
    private Long userId;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}
