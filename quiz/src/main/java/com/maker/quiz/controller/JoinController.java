package com.maker.quiz.controller;

import com.maker.quiz.dto.JoinForm;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinController {
    private final MemberService memberService;

    @GetMapping
    public String getJoin(Model model, JoinForm joinForm) {
        model.addAttribute("page", "join");
        model.addAttribute("joinForm", joinForm);
        return "layout";
    }

    @PostMapping
    public String getJoin(HttpServletRequest request, @Valid JoinForm joinForm) {
        HttpSession session = request.getSession();
        try {
            Member member = new Member();
            member.setId(joinForm.getId());
            member.setPassword(joinForm.getPassword());
            member.setName(joinForm.getName());
            memberService.join(member);
            session.setAttribute("message", "회원가입 성공");
        } catch (IllegalStateException illegalStateException){
            session.setAttribute("message", "이미 존재하는 회원입니다.");
        }catch (Exception exception){
            session.setAttribute("message", "회원가입 실패");
        }
        return "redirect:/login";
    }
}
