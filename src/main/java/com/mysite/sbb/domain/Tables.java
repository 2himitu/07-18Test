package com.mysite.sbb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Tables {
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String title;
    private String body;
    private long userId;
}
