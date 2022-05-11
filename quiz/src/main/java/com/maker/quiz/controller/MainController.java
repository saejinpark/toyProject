package com.maker.quiz.controller;
import com.maker.quiz.dto.QuizSetForm;
import com.maker.quiz.dto.ToDoForm;
import com.maker.quiz.entity.Member;
import com.maker.quiz.entity.QuizSet;
import com.maker.quiz.entity.ToDo;
import com.maker.quiz.entity.ToDoStatus;
import com.maker.quiz.service.MemberService;
import com.maker.quiz.service.QuizSetService;
import com.maker.quiz.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberService memberService;
    private final ToDoService toDoService;
    private final QuizSetService quizSetService;

    @GetMapping("/")
    public String getMain(HttpServletRequest request, Model model, ToDoForm toDoForm, QuizSetForm quizSetForm) {
        HttpSession session = request.getSession();
        if (session.getAttribute("login") == null){
            return "redirect:/login";
        }
        Member member = memberService.findOne((String) session.getAttribute("member"));
        model.addAttribute("page", "main");
        model.addAttribute("toDoList", member.getToDos());
        model.addAttribute("quizSetList", member.getQuizSets());
        model.addAttribute("toDoForm", toDoForm);
        model.addAttribute("quizSetForm", quizSetForm);
        return "layout";
    }

    @PostMapping("/addToDoList")
    public String addToDo(HttpServletRequest request, @Valid ToDoForm toDoForm){
        HttpSession session = request.getSession();
        Member member = memberService.findOne((String) session.getAttribute("member"));
        ToDo toDo = new ToDo();
        toDo.setMember(member);
        toDo.setSentence(toDoForm.getSentence());
        toDo.setTodoStatus(ToDoStatus.TODO);
        toDoService.saveToDo(toDo);
        return "redirect:/";
    }

    @GetMapping("/todo/finish/{toDoId}")
    public String finishToDo(@PathVariable Long toDoId) {
        ToDo todo = toDoService.findToDo(toDoId);
        todo.setTodoStatus(ToDoStatus.FINISHED);
        toDoService.saveToDo(todo);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{toDoId}")
    public String deleteToDo(@PathVariable Long toDoId) {
        ToDo todo = toDoService.findToDo(toDoId);
        toDoService.deleteToDo(todo);
        return "redirect:/";
    }

    @GetMapping("/todo/return/{toDoId}")
    public String returnToDo(@PathVariable Long toDoId) {
        ToDo todo = toDoService.findToDo(toDoId);
        todo.setTodoStatus(ToDoStatus.TODO);
        toDoService.saveToDo(todo);
        return "redirect:/";
    }

    @GetMapping("/add/quizSet")
    public String addQuizSet(HttpServletRequest request, @Valid QuizSetForm quizSetForm){
        HttpSession session = request.getSession();
        QuizSet quizSet = new QuizSet();
        quizSet.setName(quizSetForm.getName());
        Member member = memberService.findOne((String) session.getAttribute("member"));
        quizSet.setMember(member);
        quizSetService.saveQuizSet(quizSet);
        return "redirect:/";
    }

}