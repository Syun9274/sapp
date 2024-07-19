package com.ll.sapp.domain.surl.surl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.sapp.domain.member.member.entity.Member;
import com.ll.sapp.global.jpa.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Surl extends BaseTime {

    @ManyToOne
    @JsonIgnore
    private Member author;
    private String body;
    private String url;

    @Setter(AccessLevel.NONE)
    private long count;

    public void increaseCount() {
        count++;
    }

}
