package com.ll.sapp.domain.member.member.service;

import com.ll.sapp.domain.member.member.entity.Member;
import com.ll.sapp.domain.member.member.repository.MemberRepository;
import com.ll.sapp.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public RsData<Member> join(String username, String password, String nickname) {
        boolean present = findByUsername(username).isPresent();

        if (present) {
            return RsData.of("400-1", "이미 존재하는 아이디입니다.",
                    Member.builder().build());
        }

        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return RsData.of("회원가입이 완료되었습니다.", member);
    }

    private Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
