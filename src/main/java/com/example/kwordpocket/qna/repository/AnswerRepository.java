package com.example.kwordpocket.qna.repository;

import com.example.kwordpocket.qna.entity.Answer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByIdAndQuestionId(Long id, Long questionId);
}
