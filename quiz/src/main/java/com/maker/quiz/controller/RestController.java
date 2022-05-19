package com.maker.quiz.controller;

import com.maker.quiz.service.NaverApiEncycSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    private final NaverApiEncycSearchService naverApiEncycSearchService;

    @GetMapping("/message_reset")
    public void messageReset(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("message", null);
    }

    @GetMapping("/set_navbar/{CheckIndex}")
    public void setNavbar(HttpServletRequest request, @PathVariable Integer CheckIndex) {
        HttpSession session = request.getSession();
        session.setAttribute("navbarCheck", CheckIndex);
    }

    @GetMapping("/set_quiz_set/{CheckIndex}")
    public void setQuizSet(HttpServletRequest request, @PathVariable Integer CheckIndex) {
        HttpSession session = request.getSession();
        session.setAttribute("quizSetCheck", CheckIndex);
    }

    @GetMapping("/naver/search/encyc/{searchText}")
    @ResponseBody
    public Object searchEncyc(@PathVariable String searchText) {
        return naverApiEncycSearchService.search(searchText);
    }

}
