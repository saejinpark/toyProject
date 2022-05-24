package com.maker.quiz.controller;

import com.maker.quiz.dto.LoginForm;
import com.maker.quiz.entity.Member;
import com.maker.quiz.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class  LoginController {
    private final MemberService memberService;

    @GetMapping
    public String getLogin(HttpServletRequest request, Model model, LoginForm loginForm) {
        model.addAttribute("page", "login");
        HttpSession session = request.getSession();
        model.addAttribute("loginForm", loginForm);
        if (session.getAttribute("login") != null){
            return "redirect:/";
        }
        return "layout";
    }

    @PostMapping
    public String postLogin(HttpServletRequest request, @Valid LoginForm loginForm) {
        HttpSession session = request.getSession();
        try {
            Member member = memberService.findOne(loginForm.getId());
            if(member.getPassword().equals(loginForm.getPassword())){
                session.setAttribute("login", true);
                session.setAttribute("member", member.getId());
                session.setAttribute("navbarCheck", 0);
                session.setAttribute("quizSetCheck", 0);
                session.setAttribute("message", "로그인");
                return "redirect:/";
            }else {
                session.setAttribute("message", "비밀번호가 올바르지 않습니다.");
                return "redirect:/login";
            }
        }catch (Exception e){
            session.setAttribute("message", "아이디가 존재하지 않습니다.");
            return "redirect:/login";
        }
    }
}
