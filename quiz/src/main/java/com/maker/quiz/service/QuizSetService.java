package com.maker.quiz.service;

import com.maker.quiz.entity.Member;
import com.maker.quiz.entity.Quiz;
import com.maker.quiz.entity.QuizSet;
import com.maker.quiz.repository.QuizSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizSetService {
    private final QuizSetRepository quizSetRepository;
    private final QuizService quizService;

    @Transactional
    public Long saveQuizSet(QuizSet quizSet){
        quizSetRepository.save(quizSet);
        return quizSet.getId();
    }

    public QuizSet findQuizSet(Long id) {
        return quizSetRepository.findOne(id);
    }

    @Transactional
    public Long deleteQuizSet(QuizSet quizSet){
        Long deletedQuizSetId = quizSet.getId();
        for(Quiz quiz : quizSet.getQuizList()){
            quizService.deleteQuiz(quiz);
        }
        quizSetRepository.delete(quizSet);
        return deletedQuizSetId;
    }

    @Transactional
    public void up(QuizSet quizSet){
        Member member = quizSet.getMember();
        List<QuizSet> quizSetList = member.getQuizSetList();
        int quizSetIndex = quizSetList.indexOf(quizSet);
        if(quizSetIndex == 0){
            for(int i=0; i<quizSetList.size()-1; i++){
                Collections.swap(quizSetList , i, i+1);
            }
        }else {
            Collections.swap(quizSetList , quizSetIndex-1, quizSetIndex);
        }
    }

    @Transactional
    public void down(QuizSet quizSet){
        Member member = quizSet.getMember();
        List<QuizSet> quizSetList = member.getQuizSetList();
        int quizSetIndex = quizSetList.indexOf(quizSet);
        if(quizSetIndex == quizSetList.size()-1){
            for(int i=quizSetList.size()-1; i > 0; i--){
                Collections.swap(quizSetList , i-1, i);
            }
        }else {
            Collections.swap(quizSetList , quizSetIndex, quizSetIndex+1);
        }
    }
}
