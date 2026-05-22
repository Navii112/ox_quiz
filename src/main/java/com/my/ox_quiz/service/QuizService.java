package com.my.ox_quiz.service;

import com.my.ox_quiz.entity.Quiz;
import com.my.ox_quiz.repository.QuizRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;

    public Quiz getRandomQuiz() {
        return quizRepository.findRandomQuiz().orElse(null);
    }

    public boolean checkAnswer(Long quizId, Boolean userAnswer) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀴즈입니다."));

        return quiz.getAnswer().equals(userAnswer);
    }

    public List<Quiz> findAllQuizzes() {
        return quizRepository.findAll();
    }


    public Quiz findById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀴즈입니다."));
    }

    @Transactional
    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Transactional
    public void updateQuiz(Long id, String content, Boolean answer, String writer) {
        Quiz quiz = findById(id);
        quiz.setContent(content);
        quiz.setAnswer(answer);
        quiz.setWriter(writer);
    }

    @Transactional
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}