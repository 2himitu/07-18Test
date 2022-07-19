package com.mysite.sbb.service;

import com.mysite.sbb.dao.ArticleRepository;
import com.mysite.sbb.domain.Article;
import com.mysite.sbb.util.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> getList(Model model){
        return articleRepository.findAll();
    }

    public Article getArticle(Integer id){
        Optional<Article> article = this.articleRepository.findById(id);
        if(article.isPresent()){
            return article.get();
        }else{
            throw new DataNotFoundException("question not found");
        }
    }
    public void upViewArticle(Integer id){
        Article article = articleRepository.findById(id).get();// 조건에 맞는 데이터 가져오기
        int upView=article.getViews()+1;
        if( article != null ) {
            article.setViews(upView); //불러온 데이터 수정
        }
        articleRepository.save(article); //수정된 데이터 db에 저장
    }


}
