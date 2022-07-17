package com.maker.quiz.repository;

import com.maker.quiz.entity.Journal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JournalRepository {
    private final EntityManager em;

    public void save(Journal journal) {

        if(journal.getId() == null){
            em.persist(journal);
        }else {
            em.merge(journal);
        }
    }

    public Journal findOne(Long id) {
        return em.find(Journal.class, id);
    }

    public void delete(Journal journal) {
        em.remove(journal);
    }
}
