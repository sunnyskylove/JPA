package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

// 2개의 embedded 중 사용될때 여기에서 가져올수 있게~~
@Embeddable
public class LikedCompositeKey {

    @Embedded
    private LikedMemberNo memberNo;

    @Embedded
    private LikedBookNo bookNo;

    protected LikedCompositeKey() {}


    public LikedCompositeKey(LikedMemberNo likedMemberNo, LikedBookNo likedBookNo) {
        this.memberNo = likedMemberNo;
        this.bookNo = likedBookNo;
    }
}
