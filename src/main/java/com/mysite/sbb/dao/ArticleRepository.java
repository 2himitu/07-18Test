package com.mysite.sbb.dao;

import com.mysite.sbb.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository <Article,Integer> {

}
