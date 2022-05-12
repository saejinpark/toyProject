package com.maker.quiz.repository;

import com.maker.quiz.entity.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class QuizRepository {
    private final EntityManager em;

    public void save(Quiz quiz){
        if(quiz.getId() == null){
            em.persist(quiz);
        }else {
            em.merge(quiz);
        }
    }

    public Quiz findOne(Long id) {
        return em.find(Quiz.class, id);
    }

    public void delete(Quiz quiz) {
        em.remove(quiz);
    }
}
