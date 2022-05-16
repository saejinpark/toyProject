package com.maker.quiz.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id @NotEmpty
    private String id;

    private String name;

    private String password;

    @OneToMany(mappedBy = "member")
    @OrderColumn(name = "QUIZ_SET_ORDER")
    private List<QuizSet> quizSetList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ToDo> toDoList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Journal> journalList = new ArrayList<>();
}