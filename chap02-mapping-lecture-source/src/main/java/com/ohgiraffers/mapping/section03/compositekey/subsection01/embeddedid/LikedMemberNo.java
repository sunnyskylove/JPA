package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class LikedMemberNo {

    @Column(name = "liked_book_no")
    private int likedBookNo;

    protected LikedMemberNo() {}

    public LikedMemberNo(int likedBookNo) {
        this.likedBookNo = likedBookNo;
    }


}
