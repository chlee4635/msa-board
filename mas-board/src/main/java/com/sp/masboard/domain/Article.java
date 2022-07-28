package com.sp.masboard.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @Setter private String hashtag; // 해시태그

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; // 생성일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //수정자

    protected Article() {}

    // 메타데이터를 제외한 생성자만 생성
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // 팩토리 메서드를 사용
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    // 컬렉션의 동일성, 동등성 확장
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        // jdk 14 이상번을 쓸경우, pattern matching을 사용
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        
        // 영속화가 되지않은 경우, 체크 필요.
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
