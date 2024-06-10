package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/* 참고. 예약어 항상 조심!! */
@Entity
@Table(name = "tbl_like")       // 예약어로 like가 있어서 예약어로 만들면 테이블 안만들어짐.
public class Like {

    /* 참고. 우리가 세트로 묶어 둔 memberNo, bookNo 를 ID(pk)로서 사용! */
    @EmbeddedId
    private LikedCompositeKey likeInfo;

    protected Like() {}

    public Like(LikedCompositeKey likeInfo) {
        this.likeInfo = likeInfo;
    }


}
