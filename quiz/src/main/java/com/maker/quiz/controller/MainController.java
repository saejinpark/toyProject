package com.maker.quiz.controller;
import com.maker.quiz.dto.*;
import com.maker.quiz.entity.*;
import com.maker.quiz.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberService memberService;
    private final ToDoService toDoService;
    private final QuizSetService quizSetService;
    private final QuizService quizService;
    private final JournalService journalService;

    @GetMapping("/")
    public String getMain(
            HttpServletRequest request,
            Model model, ToDoForm toDoForm,
            QuizSetForm quizSetForm, QuizForm quizForm,
            JournalForm journalForm
    ) {
        HttpSession session = request.getSession();
        if (session.getAttribute("login") == null){
            return "redirect:/login";
        }
        Member member = memberService.findOne((String) session.getAttribute("member"));
        model.addAttribute("page", "main");
        model.addAttribute("toDoList", member.getToDoList());
        model.addAttribute("quizSetList", member.getQuizSetList());
        model.addAttribute("journalList", member.getJournalList());
        model.addAttribute("toDoForm", toDoForm);
        model.addAttribute("quizSetForm", quizSetForm);
        model.addAttribute("quizForm", quizForm);
        model.addAttribute("journalForm", journalForm);
        model.addAttribute("yearList", member.getJournalYearList());
        model.addAttribute("monthList", member.getJournalMonthList());
        model.addAttribute("dateList", member.getJournalDateList());
        model.addAttribute("dayList", member.getJournalDayList());
        return "layout";
    }

    @GetMapping("/delete/{memberId}")
    public String deleteMember(@PathVariable String memberId){
        Member member = memberService.findOne(memberId);
        memberService.deleteMember(member);
        return "redirect:/logout";
    }

    @PostMapping("/toDoList/add")
    public String toDoAdd(HttpServletRequest request, @Valid ToDoForm toDoForm){
        HttpSession session = request.getSession();
        Member member = memberService.findOne((String) session.getAttribute("member"));
        ToDo toDo = new ToDo();
        toDo.setMember(member);
        toDo.setSentence(toDoForm.getSentence());
        toDo.setTodoStatus(ToDoStatus.TODO);
        toDoService.saveToDo(toDo);
        return "redirect:/";
    }

    @GetMapping("/toDo/finish/{toDoId}")
    public String toDoFinish(@PathVariable Long toDoId) {
        ToDo todo = toDoService.findToDo(toDoId);
        todo.setTodoStatus(ToDoStatus.FINISHED);
        toDoService.saveToDo(todo);
        return "redirect:/";
    }

    @GetMapping("/toDo/delete/{toDoId}")
    public String toDoDelete(@PathVariable Long toDoId) {
        ToDo todo = toDoService.findToDo(toDoId);
        toDoService.deleteToDo(todo);
        return "redirect:/";
    }

    @GetMapping("/toDo/return/{toDoId}")
    public String toDoReturn(@PathVariable Long toDoId) {
        ToDo todo = toDoService.findToDo(toDoId);
        todo.setTodoStatus(ToDoStatus.TODO);
        toDoService.saveToDo(todo);
        return "redirect:/";
    }

    @GetMapping("/quizSet/add")
    public String quizSetAdd(HttpServletRequest request, @Valid QuizSetForm quizSetForm){
        HttpSession session = request.getSession();
        QuizSet quizSet = new QuizSet();
        quizSet.setName(quizSetForm.getName());
        Member member = memberService.findOne((String) session.getAttribute("member"));
        quizSet.setMember(member);
        quizSetService.saveQuizSet(quizSet);
        return "redirect:/";
    }

    @GetMapping("/quizSet/delete/{quizSetId}")
    public String quizSetDelete(@PathVariable Long quizSetId){
        QuizSet quizSet = quizSetService.findQuizSet(quizSetId);
        quizSetService.deleteQuizSet(quizSet);
        return "redirect:/";
    }

    @PostMapping("/quizSet/edit/{quizSetId}")
    public String QuizSetEdit(@PathVariable Long quizSetId, @Valid QuizSetForm quizSetForm){
        QuizSet quizSet = quizSetService.findQuizSet(quizSetId);
        quizSet.setName(quizSetForm.getName());
        quizSetService.saveQuizSet(quizSet);
        return "redirect:/";
    }

    @GetMapping("/quizSet/up/{quizSetId}")
    public String quizSetUp(HttpServletRequest request, @PathVariable Long quizSetId){
        HttpSession session = request.getSession();
        Member member = memberService.findOne((String) session.getAttribute("member"));
        List<QuizSet> quizSetList = member.getQuizSetList();
        int quizSetCheck = (int)session.getAttribute("quizSetCheck");
        if(quizSetCheck == 0){
            session.setAttribute("quizSetCheck", quizSetList.size()-1);
        }else {
            session.setAttribute("quizSetCheck", quizSetCheck-1);
        }
        QuizSet quizSet = quizSetService.findQuizSet(quizSetId);
        quizSetService.up(quizSet);
        return "redirect:/";
    }

    @GetMapping("/quizSet/down/{quizSetId}")
    public String quizSetDown(HttpServletRequest request, @PathVariable Long quizSetId){
        HttpSession session = request.getSession();
        Member member = memberService.findOne((String) session.getAttribute("member"));
        List<QuizSet> quizSetList = member.getQuizSetList();
        int quizSetCheck = (int)session.getAttribute("quizSetCheck");
        if(quizSetCheck == quizSetList.size()-1){
            session.setAttribute("quizSetCheck", 0);
        }else {
            session.setAttribute("quizSetCheck", quizSetCheck+1);
        }
        QuizSet quizSet = quizSetService.findQuizSet(quizSetId);
        quizSetService.down(quizSet);
        return "redirect:/";
    }

    @PostMapping("/quizSet/{quizSetId}/quiz/add")
    public String quizAdd(@PathVariable Long quizSetId, QuizForm quizForm){
        QuizSet quizSet = quizSetService.findQuizSet(quizSetId);
        Quiz quiz = new Quiz();
        quiz.setQuizSet(quizSet);
        quiz.setQuiz(quizForm.getQuiz());
        quiz.setAnswer(quizForm.getAnswer());
        quizService.saveQuiz(quiz);
        return "redirect:/";
    }


    @GetMapping("/quiz/delete/{quizId}")
    public String quizAdd(@PathVariable Long quizId){
        Quiz quiz = quizService.findQuiz(quizId);
        quizService.deleteQuiz(quiz);
        return "redirect:/";
    }

    @PostMapping("/journal/add")
    public String journalAdd(HttpServletRequest request, JournalForm journalForm){
        HttpSession session = request.getSession();
        Member member = memberService.findOne((String) session.getAttribute("member"));
        Journal journal = new Journal();
        journal.setTitle(journalForm.getTitle());
        journal.setDetail(journalForm.getDetail());
        journal.setGeneratedTime(new Date());
        journal.setMember(member);
        journalService.saveJournal(journal);
        return "redirect:/";
    }


    @GetMapping("/journal/delete/{journalId}")
    public String journalDelete(@PathVariable Long journalId){
        Journal journal = journalService.findJournal(journalId);
        journalService.deleteJournal(journal);
        return "redirect:/";
    }
}