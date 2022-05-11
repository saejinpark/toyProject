package com.maker.quiz.repository;

import com.maker.quiz.entity.QuizSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuizSetRepository {
    private final EntityManager em;
    public void save(QuizSet quizSet) {
        if(quizSet.getId() == null){
            em.persist(quizSet);
        }else {
            em.merge(quizSet);
        }
    }

    public QuizSet findOne(Long id) {
        return em.find(QuizSet.class, id);
    }

    public void delete(QuizSet quizSet) {
        em.remove(quizSet);
    }
}
