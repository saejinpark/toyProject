package com.maker.quiz.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ToDo {

    @Id @GeneratedValue
    private Long id;

    private String sentence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private ToDoStatus todoStatus;

    public void setMember(Member member) {
        this.member = member;
        member.getToDoList().add(this);
    }
}
