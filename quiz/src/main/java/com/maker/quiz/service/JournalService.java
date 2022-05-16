package com.maker.quiz.service;

import com.maker.quiz.entity.Journal;
import com.maker.quiz.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JournalService {
    public final JournalRepository journalRepository;

    @Transactional
    public Long saveJournal(Journal journal){
        journalRepository.save(journal);
        return journal.getId();
    }

    public Journal findJournal(Long id) {
        return journalRepository.findOne(id);
    }

    @Transactional
    public Long deleteJournal(Journal journal){
        Long deletedJournalId = journal.getId();
        journalRepository.delete(journal);
        return deletedJournalId;
    }

}
