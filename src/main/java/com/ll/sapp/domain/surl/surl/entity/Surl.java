package com.ll.sapp.domain.surl.surl.entity;

import com.ll.sapp.global.jpa.entity.BaseTime;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Surl extends BaseTime {

    private String body;
    private String url;

    @Setter(AccessLevel.NONE)
    private long count;

    public void increaseCount() {
        count++;
    }

}
