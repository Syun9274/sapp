package com.ll.sapp.domain.member.member.entity;

import com.ll.sapp.global.jpa.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {

    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;
}
