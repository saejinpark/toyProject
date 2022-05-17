package com.maker.quiz.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Journal {
    @Id @GeneratedValue
    private Long id;


    private String title;

    private String detail;

    @Temporal(TemporalType.DATE)
    @Column(name = "GENERATED_TIME")
    private Date generatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getJournalList().add(this);
    }

    public String getTimeText() {
        return getGeneratedYear()  + "년 " + getGeneratedMonth()  + "월 " + getGeneratedDate() + "일 " +  getGeneratedDay() + "요일";
    }
    public int getGeneratedYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(generatedTime);
        return calendar.get(Calendar.YEAR);
    }
    public int getGeneratedMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(generatedTime);
        return calendar.get(Calendar.MONTH) + 1;
    }
    public int getGeneratedDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(generatedTime);
        return calendar.get(Calendar.DATE);
    }

    public String getGeneratedDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(generatedTime);
        String[] day = new String[]{"일", "월", "화", "수", "목", "금", "토"};
        return day[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }
}
