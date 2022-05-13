package com.maker.quiz.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class QuizSet {

    @Id
    @GeneratedValue
    @Column(name="quiz_set_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "quizSet")
    private List<Quiz> quizList = new ArrayList<>();

    public void setMember(Member member){
        this.member = member;
        member.getQuizSetList().add(this);
    }
}
