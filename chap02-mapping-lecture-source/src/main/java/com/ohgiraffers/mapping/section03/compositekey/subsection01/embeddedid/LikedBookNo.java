package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class LikedBookNo {

    @Column(name = "liked_member_no")
    private int likedMemberNo;

    protected LikedBookNo() {}

    public LikedBookNo(int likedMemberNo) {
        this.likedMemberNo = likedMemberNo;
    }
}
