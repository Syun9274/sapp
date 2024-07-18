package com.ll.sapp.global.initData;

import com.ll.sapp.domain.article.article.entity.Article;
import com.ll.sapp.domain.article.article.service.ArticleService;
import com.ll.sapp.domain.member.member.entity.Member;
import com.ll.sapp.domain.member.member.service.MemberService;
import com.ll.sapp.global.exceptions.GlobalException;
import com.ll.sapp.global.rsData.RsData;
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

        try {
            RsData<Member> joinRs = memberService.join("user2", "1234", "유저 2");
        } catch (GlobalException e) {
            System.out.println("e.getMsg() : " + e.getRsData().getMsg());
            System.out.println("e.getStatusCode() : " + e.getRsData().getStatusCode());
        }

        Article article1 = articleService.write("제목 1", "내용 1").getData();
        Article article2 = articleService.write("제목 2", "내용 2").getData();

        article2.setTitle("제목 !!");

        articleService.delete(article1);
    }

    @Transactional
    public void work2() {
        Article article = articleService.findById(2L).get();
        List<Article> articles = articleService.findAll();
    }

}

