package example.ToDoList.Controller;

import example.ToDoList.Dto.ToDoForm;
import example.ToDoList.Service.ToDoService;
import example.ToDoList.domain.FinishStatus;
import example.ToDoList.domain.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ToDoListController {

    private final ToDoService toDoService;

    @GetMapping("/")
    public String getToDoList(Model model){
        List<Object> toDoList = new ArrayList<>();
        for(ToDo todo : toDoService.findToDos()){
            HashMap<String, Object> toDo = new HashMap<>();
            toDo.put("id", todo.getId());
            toDo.put("sentence", todo.getSentence());
            toDo.put("finishStatus", todo.getFinishStatus().name());
            toDoList.add(toDo);
        }
        model.addAttribute("toDoList", toDoList);
        model.addAttribute("toDoForm", new ToDoForm());
        return "toDoList";
    }

    @PostMapping("/")
    public String postToDoList(@Valid ToDoForm toDoForm){
        ToDo toDo = new ToDo();
        toDo.setSentence(toDoForm.getSentence());
        toDo.setFinishStatus(FinishStatus.TODO);
        toDoService.saveTodo(toDo);
        return "redirect:/";
    }

    @GetMapping("/finish/{toDoId}")
    public String finishToDoList(@PathVariable Long toDoId){
        ToDo toDo = toDoService.findToDo(toDoId);
        toDo.setFinishStatus(FinishStatus.FINISH);
        toDoService.saveTodo(toDo);
        return "redirect:/";
    }

    @GetMapping("/return/{toDoId}")
    public String returnToDoList(@PathVariable Long toDoId){
        ToDo toDo = toDoService.findToDo(toDoId);
        toDo.setFinishStatus(FinishStatus.TODO);
        toDoService.saveTodo(toDo);
        return "redirect:/";
    }

    @GetMapping("/delete/{toDoId}")
    public String deleteToDoList(@PathVariable Long toDoId){
        ToDo toDo = toDoService.findToDo(toDoId);
        toDoService.deleteTodo(toDo);
        return "redirect:/";
    }
}
