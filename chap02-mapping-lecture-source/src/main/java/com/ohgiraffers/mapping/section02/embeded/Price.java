package com.ohgiraffers.mapping.section02.embeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/* 필기. embeded 가 될 수 있는 타입을 지정할 때 사용한다. */
@Embeddable
// embeded 로 끌어와질 상태로 만들어야 한다!
public class Price {

    @Column(name = "regular_price")
    private int regularPrice;

    @Column(name = "discount_rate")
    private double discountRate;

    // *참고: 위 2개를 추가로 만들고, 이 2가지를 합친 값에 대한 sell_price 를 추가로 또 칼럼 생성!
    @Column(name = "sell_price")
    private int sellPrice;

    // *참고: 기본 생성자를 protected 로 해서 price 로 만든다.
    protected Price() {}


    public Price(int regularPrice, double discountRate) {
        validateNegativePrice(regularPrice);
        validateNegativeDiscountRate(discountRate);
        this.regularPrice = regularPrice;
        this.discountRate = discountRate;
        this.sellPrice = calcSellPrice(regularPrice, discountRate);

    }

    private int calcSellPrice(int regularPrice, double discountRate) {

        return (int) (regularPrice - (regularPrice * discountRate));

    }

    private void validateNegativeDiscountRate(double discountRate) {

        if(discountRate <0 ) {
            throw new IllegalArgumentException("할인율은 음수일 수 없습니다.");

        }

    }

    private void validateNegativePrice(int regularPrice) {

        if(regularPrice < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }
    }

}
