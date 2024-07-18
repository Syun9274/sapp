package com.ll.sapp.global.initData;

import com.ll.sapp.domain.article.article.entity.Article;
import com.ll.sapp.domain.article.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final ArticleRepository articleRepository;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {

            if(articleRepository.count() > 0) return;

            Article article1 = Article.builder()
                    .title("제목 1")
                    .body("내용 1")
                    .build();

            Article article2 = Article.builder()
                    .title("제목 2")
                    .body("내용 2")
                    .build();

            System.out.println("articleFirst.id : " + article1.getId());
            System.out.println("articleSecond.id : " + article2.getId());

            articleRepository.save(article1);
            articleRepository.save(article2);

            System.out.println("articleFirst.id : " + article1.getId());
            System.out.println("articleSecond.id : " + article2.getId());

            articleRepository.delete(article1);
        };
    }

}
