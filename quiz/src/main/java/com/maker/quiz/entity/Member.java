package com.maker.quiz.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private List<QuizSet> quizSets = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ToDo> toDos = new ArrayList<>();
}