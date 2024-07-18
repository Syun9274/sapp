package com.ll.sapp.domain.article.article.repository;

import com.ll.sapp.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
