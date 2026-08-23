package com.example.kwordpocket.qna.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerUpdateRequest {

    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}
