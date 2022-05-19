package com.maker.quiz.service;
import com.maker.quiz.api.NaverApiEncycSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverApiEncycSearchService{
    private final NaverApiEncycSearch naverApiEncycSearch;

    public Object search(String searchText){
        return naverApiEncycSearch.seach(searchText);
    }
}
