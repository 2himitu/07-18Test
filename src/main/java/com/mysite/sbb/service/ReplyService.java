package com.mysite.sbb.service;


import com.mysite.sbb.dao.ReplyRepository;
import com.mysite.sbb.domain.Reply;
import com.mysite.sbb.domain.Article;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void create(Article article, String content) {
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setCreateDate(LocalDateTime.now());
        reply.setArticle(article);
        reply.setReplyLike(false);
        this.replyRepository.save(reply);
    }
    public void setLike(Integer answerId) {
        Reply reply = replyRepository.findById(answerId).get();
        if(reply.getReplyLike() == true) {
            reply.setReplyLike(false);
        } else {
            reply.setReplyLike(true);
        }
        this.replyRepository.save(reply);
    }
}