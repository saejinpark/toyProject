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

    public List<Integer> getJournalYearList() {
        List<Integer> yearList = new ArrayList<>();
        for(Journal journal : journalList){
            Integer year = journal.getGeneratedYear();
            if(!yearList.contains(year)){
                yearList.add(year);
            }
        }
        return yearList;
    }

    public List<Integer> getJournalMonthList() {
        List<Integer> monthList = new ArrayList<>();
        for(Journal journal : journalList){
            Integer month = journal.getGeneratedMonth();
            if(!monthList.contains(month)){
                monthList.add(month);
            }
        }
        return monthList;
    }

    public List<Integer> getJournalDateList() {
        List<Integer> dateList = new ArrayList<>();
        for(Journal journal : journalList){
            Integer date = journal.getGeneratedDate();
            if(!dateList.contains(date)){
                dateList.add(date);
            }
        }
        return dateList;
    }


    public List<String> getJournalDayList() {
        List<String> dayList = new ArrayList<>();
        for(Journal journal : journalList){
            String day = journal.getGeneratedDay();
            if(!dayList.contains(day)){
                dayList.add(day);
            }
        }
        return dayList;
    }

}