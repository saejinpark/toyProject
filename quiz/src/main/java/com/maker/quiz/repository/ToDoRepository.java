package com.maker.quiz.repository;

import com.maker.quiz.entity.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ToDoRepository {
    private final EntityManager em;
    public void save(ToDo toDo) {
        if(toDo.getId() == null){
            em.persist(toDo);
        }else {
            em.merge(toDo);
        }
    }

    public ToDo findOne(Long id) {
        return em.find(ToDo.class, id);
    }

    public void delete(ToDo toDo) {
        em.remove(toDo);
    }

}
