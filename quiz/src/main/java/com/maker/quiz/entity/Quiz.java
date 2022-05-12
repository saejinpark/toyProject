package com.maker.quiz.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue
    @Column(name="quiz_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_set_id")
    private QuizSet quizSet;

    private String quiz;

    private String answer;

    public void setQuizSet(QuizSet quizSet){
        this.quizSet = quizSet;
        quizSet.getQuizList().add(this);
    }
}
