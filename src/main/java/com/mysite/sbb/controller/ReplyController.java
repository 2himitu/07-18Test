package com.mysite.sbb.controller;

import com.mysite.sbb.domain.Article;
import com.mysite.sbb.service.ArticleService;
import com.mysite.sbb.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reply")
@AllArgsConstructor
public class ReplyController {

    private final ArticleService articleService;
    private final ReplyService replyService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
        Article article = this.articleService.getArticle(id);
        // 질문만들기
        this.replyService.create(article, content);
        return String.format("redirect:/article/detail/%s", id);
    }


    @PostMapping("/like/{articleId}/{replyId}")
    public String createAnswer(@PathVariable("articleId") Integer articleId, @PathVariable("replyId") Integer replyId) {
        this.replyService.setLike(replyId);

        return String.format("redirect:/article/detail/%s", articleId);
    }

}