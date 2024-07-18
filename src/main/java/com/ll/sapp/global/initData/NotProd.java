package com.ll.sapp.global.initData;

import com.ll.sapp.domain.article.article.entity.Article;
import com.ll.sapp.domain.article.article.service.ArticleService;
import com.ll.sapp.domain.member.member.entity.Member;
import com.ll.sapp.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("!prod")
@Configuration
@RequiredArgsConstructor
public class NotProd {

    @Lazy
    @Autowired
    private NotProd self;
    private ArticleService articleService;
    private MemberService memberService;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work1() {
        if(articleService.count() > 0) return;

        Member member1 = memberService.join("user1", "1234", "유저 1").getData();
        Member member2 = memberService.join("user2", "1234", "유저 2").getData();

        Article article1 = articleService.write(member1,"제목 1", "내용 1").getData();
        Article article2 = articleService.write(member1,"제목 2", "내용 2").getData();
        Article article3 = articleService.write(member2,"제목 3", "내용 3").getData();
        Article article4 = articleService.write(member2,"제목 4", "내용 4").getData();


    }

    @Transactional
    public void work2() {
        Article article = articleService.findById(2L).get();
        List<Article> articles = articleService.findAll();
    }

}

