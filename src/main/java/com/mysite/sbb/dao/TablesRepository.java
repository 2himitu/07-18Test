package com.mysite.sbb.dao;

import com.mysite.sbb.domain.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TablesRepository extends JpaRepository<Tables, Long> {
    ArrayList<Tables> Articles =  new ArrayList<>();

    List<Tables> findByTitle(String title);

    boolean existsByTitle(String title);

    boolean existsByBody(String body);

    List<Tables> findByBody(String body);

    boolean existsByTitleAndBody(String title, String body);

    List<Tables> findByTitleAndBody(String title, String body);
}
