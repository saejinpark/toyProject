package com.maker.quiz.service;

import com.maker.quiz.entity.Quiz;
import com.maker.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {
    private final QuizRepository quizRepository;

    @Transactional
    public Long saveQuiz(Quiz quiz){
        quizRepository.save(quiz);
        return quiz.getId();
    }

    public Quiz findQuiz(Long id) {
        return quizRepository.findOne(id);
    }

    @Transactional
    public Long deleteQuiz(Quiz quiz){
        Long deletedQuizId = quiz.getId();
        quizRepository.delete(quiz);
        return deletedQuizId;
    }
}
