package com.maker.quiz.service;

import com.maker.quiz.entity.QuizSet;
import com.maker.quiz.repository.QuizSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizSetService {
    private final QuizSetRepository quizSetRepository;

    @Transactional
    public Long saveQuizSet(QuizSet quizSet){
        quizSetRepository.save(quizSet);
        return quizSet.getId();
    }

    public QuizSet findQuizSet(Long id) {
        return quizSetRepository.findOne(id);
    }

    @Transactional
    public Long deleteQuizSet(QuizSet quizSet){
        Long deletedQuizSetId = quizSet.getId();
        quizSetRepository.delete(quizSet);
        return deletedQuizSetId;
    }
}
