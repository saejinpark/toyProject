package com.maker.quiz.service;

import com.maker.quiz.entity.ToDo;
import com.maker.quiz.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    @Transactional
    public Long saveToDo(ToDo toDo){
        toDoRepository.save(toDo);
        return toDo.getId();
    }

    public ToDo findToDo(Long id) {
        return toDoRepository.findOne(id);
    }

    @Transactional
    public Long deleteToDo(ToDo toDo){
        Long deletedToDoId = toDo.getId();
        toDoRepository.delete(toDo);
        return deletedToDoId;
    }

}
