package com.mysite.sbb.controller;

import com.mysite.sbb.dao.TablesRepository;
import com.mysite.sbb.domain.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/table")
public class TablesController {
    @Autowired
    private TablesRepository tablesRepository;
    @RequestMapping("/test")
    @ResponseBody
    public String testFunc() {
        return "test";
    }
    @RequestMapping("/list")
    @ResponseBody
    public List<Tables> showTablesList(String title, String body) {
        if(title != null && body == null) {
            if(tablesRepository.existsByTitle(title) == false) {
                System.out.println("제목과 일치하는 게시물이 없습니다.");
                return null;
            }
            return tablesRepository.findByTitle(title);
        } else if(title == null && body != null) {
            if(tablesRepository.existsByBody(body) == false) {
                System.out.println("내용과 일치하는 게시물이 없습니다.");
                return null;
            }
            return tablesRepository.findByBody(body);
        } else if(title != null && body != null) {
            if(tablesRepository.existsByTitleAndBody(title, body) == false) {
                System.out.println("제목, 내용과 일치하는 게시물이 없습니다.");
                return null;
            }
            return tablesRepository.findByTitleAndBody(title, body);
        }
        return tablesRepository.findAll();
    }
    @RequestMapping("/detail")
    @ResponseBody
    public Tables showTablesDetail(@RequestParam long id, String name) {
        Optional<Tables> tables = tablesRepository.findById(id);
        return tables.orElse(null);
    }
    @RequestMapping("/doModify")
    @ResponseBody
    public Tables doModify(long id, String title, String body) {
        Tables tables = tablesRepository.findById(id).get();// 조건에 맞는 데이터 가져오기
        if( title != null ) {
            tables.setTitle(title); //불러온 데이터 수정
        }
        if( body != null ) {
            tables.setBody(body); //불러온 데이터 수정
        }
        tables.setUpdateDate(LocalDateTime.now());
        tablesRepository.save(tables); //수정된 데이터 db에 저장
        return tables;
    }
    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(long id) {
        if(tablesRepository.existsById(id) == false) {
            return "%d번 게시물은 이미 삭제되었거나 존재하지 않습니다.".formatted(id);
        }
        tablesRepository.deleteById(id); // 삭제
        return "%d번 게시물이 삭제되었습니다".formatted(id);

    }
    @RequestMapping("/doWrite")
    @ResponseBody
    public String doWrite(String title, String body) {
        if( title == null || title.trim().length() == 0) {
            return "제목을 입력해주세요";
        }
        if( body == null || body.trim().length() == 0) {
            return "내용을 입력해주세요";
        }

        title = title.trim();
        body = body.trim();

        Tables tables = new Tables();
        tables.setRegDate(LocalDateTime.now());
        tables.setUpdateDate(LocalDateTime.now());
        tables.setTitle(title);
        tables.setBody(body);
        tables.setUserId(33L);
        tablesRepository.save(tables);

        return "%d번 게시물이 생성되었습니다.".formatted(tables.getId());
    }
}
