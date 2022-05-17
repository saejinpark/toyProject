package com.maker.quiz.service;

import com.maker.quiz.entity.Journal;
import com.maker.quiz.entity.Member;
import com.maker.quiz.entity.QuizSet;
import com.maker.quiz.entity.ToDo;
import com.maker.quiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ToDoService toDoService;
    private final QuizSetService quizSetService;
    private final JournalService journalService;

    @Transactional
    public String saveMember(Member member){
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public String join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findById(member.getId());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member findOne(String id) {
        return memberRepository.findOne(id);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public String deleteMember(Member member){
        String deletedMemberId = member.getId();
        for(ToDo toDo :member.getToDoList()){
            toDoService.deleteToDo(toDo);
        }
        List<QuizSet> quizSetList = member.getQuizSetList();
        while(quizSetList.size() != 0){
            quizSetService.deleteQuizSet(quizSetList.get(0));
        }
        for(Journal journal : member.getJournalList()){
            journalService.deleteJournal(journal);
        }
        memberRepository.delete(member);
        return deletedMemberId;
    }

}
