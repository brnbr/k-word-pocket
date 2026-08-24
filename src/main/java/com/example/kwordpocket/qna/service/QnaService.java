package com.example.kwordpocket.qna.service;

import com.example.kwordpocket.qna.dto.AnswerCreateRequest;
import com.example.kwordpocket.qna.dto.AnswerResponse;
import com.example.kwordpocket.qna.dto.AnswerUpdateRequest;
import com.example.kwordpocket.qna.dto.QuestionCreateRequest;
import com.example.kwordpocket.qna.dto.QuestionDetailResponse;
import com.example.kwordpocket.qna.dto.QuestionResponse;
import com.example.kwordpocket.qna.dto.QuestionUpdateRequest;
import com.example.kwordpocket.qna.entity.Answer;
import com.example.kwordpocket.qna.entity.Question;
import com.example.kwordpocket.qna.exception.AnswerNotFoundException;
import com.example.kwordpocket.qna.exception.QuestionNotFoundException;
import com.example.kwordpocket.qna.repository.AnswerRepository;
import com.example.kwordpocket.qna.repository.QuestionRepository;
import com.example.kwordpocket.user.entity.User;
import com.example.kwordpocket.user.exception.UserNotFoundException;
import com.example.kwordpocket.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QnaService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    public Page<QuestionResponse> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable)
                .map(QuestionResponse::from);
    }

    public QuestionDetailResponse getQuestion(Long questionId) {
        return QuestionDetailResponse.from(findQuestionById(questionId));
    }

    @Transactional
    public QuestionResponse createQuestion(QuestionCreateRequest request, Long loginUserId) {
        User user = findUserById(loginUserId);
        Question question = Question.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        return QuestionResponse.from(questionRepository.save(question));
    }

    @Transactional
    public QuestionResponse updateQuestion(Long questionId, QuestionUpdateRequest request) {
        Question question = findQuestionById(questionId);
        question.update(request.getTitle(), request.getContent());
        questionRepository.flush();
        return QuestionResponse.from(question);
    }

    @Transactional
    public void deleteQuestion(Long questionId) {
        questionRepository.delete(findQuestionById(questionId));
    }

    public List<AnswerResponse> getAnswers(Long questionId) {
        return findQuestionById(questionId).getAnswers().stream()
                .map(AnswerResponse::from)
                .toList();
    }

    public AnswerResponse getAnswer(Long questionId, Long answerId) {
        return AnswerResponse.from(findAnswerInQuestion(questionId, answerId));
    }

    @Transactional
    public AnswerResponse createAnswer(Long questionId, AnswerCreateRequest request, Long loginUserId) {
        Question question = findQuestionById(questionId);
        User user = findUserById(loginUserId);
        Answer answer = Answer.builder()
                .question(question)
                .user(user)
                .content(request.getContent())
                .build();
        return AnswerResponse.from(answerRepository.save(answer));
    }

    @Transactional
    public AnswerResponse updateAnswer(Long questionId, Long answerId, AnswerUpdateRequest request) {
        Answer answer = findAnswerInQuestion(questionId, answerId);
        answer.update(request.getContent());
        answerRepository.flush();
        return AnswerResponse.from(answer);
    }

    @Transactional
    public void deleteAnswer(Long questionId, Long answerId) {
        answerRepository.delete(findAnswerInQuestion(questionId, answerId));
    }

    private Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException());
    }

    private Answer findAnswerInQuestion(Long questionId, Long answerId) {
        return answerRepository.findByIdAndQuestionId(answerId, questionId)
                .orElseThrow(() -> new AnswerNotFoundException());
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
    }
}
