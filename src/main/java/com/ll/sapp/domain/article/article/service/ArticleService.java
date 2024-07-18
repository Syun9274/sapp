package com.ll.sapp.domain.article.article.service;

import com.ll.sapp.domain.article.article.entity.Article;
import com.ll.sapp.domain.article.article.repository.ArticleRepository;
import com.ll.sapp.domain.member.member.entity.Member;
import com.ll.sapp.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    public long count() {
        return articleRepository.count();
    }

    @Transactional
    public RsData<Article> write(Member author, String title, String body) {
        Article article = Article.builder()
                .author(author)
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);

        return RsData.of("%d번 게시물이 작성되었습니다.".formatted(article.getId()), article);
    }

    @Transactional
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public Optional<Article> findById(long id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
