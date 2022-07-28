package com.sp.masboard.repository;

import com.sp.masboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
