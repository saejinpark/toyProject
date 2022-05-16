package com.maker.quiz.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter @Setter
public class Journal {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Date date;

    private String title;

    private String detail;

    public void setMember(Member member) {
        this.member = member;
        member.getJournalList().add(this);
    }
}
