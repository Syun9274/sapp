package com.ll.sapp.domain.article.article.entity;

import com.ll.sapp.domain.member.member.entity.Member;
import com.ll.sapp.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTime {

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    private Member author;
}
