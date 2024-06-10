package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;


public class CartCompositeKey {

    // 아래 2개를 pk로 사용할 예정
    private int cartOwner;
    private int addedBook;

    protected CartCompositeKey(){}

    public CartCompositeKey(int cartOwner, int addedBook) {
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
    }



}
