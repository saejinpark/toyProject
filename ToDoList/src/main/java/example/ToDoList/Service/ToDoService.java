package example.ToDoList.Service;

import example.ToDoList.Repository.ToDoRepository;
import example.ToDoList.domain.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ToDoService {
    private final ToDoRepository toDoRepository;

    @Transactional
    public void saveTodo(ToDo todo){
        toDoRepository.save(todo);
    }

    @Transactional
    public void deleteTodo(ToDo todo){
        toDoRepository.delete(todo);
    }

    public List<ToDo> findToDos() {
        return toDoRepository.findAll();
    }

    public ToDo findToDo(Long id) {
        return toDoRepository.findOne(id);
    }



}
