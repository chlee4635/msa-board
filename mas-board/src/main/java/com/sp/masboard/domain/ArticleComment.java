package com.sp.masboard.domain;

import java.time.LocalDateTime;

public class ArticleComment {

    private Long id;
    private Article article; // 게시글(ID) 객체지향적
    private String comment; // 본문

    private LocalDateTime createdAt; // 생성일시
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정일시
    private String modifiedBy; //수정자


}
