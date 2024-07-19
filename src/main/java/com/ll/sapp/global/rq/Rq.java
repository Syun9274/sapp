package com.ll.sapp.global.rq;

import com.ll.sapp.domain.member.member.entity.Member;
import com.ll.sapp.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {

    private final MemberService memberService;

    public Member getMember() {
        return memberService.getReferenceById(1L);
    }
}
