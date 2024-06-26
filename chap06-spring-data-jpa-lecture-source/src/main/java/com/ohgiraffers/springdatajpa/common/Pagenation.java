package com.ohgiraffers.springdatajpa.common;

import org.springframework.data.domain.Page;

public class Pagenation {

    public static PagingButton getPagingButtonInfo(Page page) {

        /* 참고. 현재 페이지에 대한 정보. +1 : 이유는 숫자 체계가 0부터 시작함. 따라서 사용자 보는 시점을 맞췄다. */
        int currentPage = page.getNumber() + 1;

        /* 참고. 페이지 버튼의 기본 개수 */
        int defaultButtonCount = 10;

        /* 참고. 현재 시작 페이지를 계산 */
        int startPage = (int)(Math.ceil((double) currentPage / defaultButtonCount ) -1) * defaultButtonCount +1;

        /* 참고. 끝페이지 계산 */
        int endPage = startPage + defaultButtonCount -1;

        /* 참고. 실제 총 페이지가 endPage 보다 작으면 endPage 를 총 페이지로 끊어준다. */

        if(page.getTotalPages() < endPage) {
            endPage = page.getTotalPages();
        }

        /*　참고. 페이지가 0이면 끝 페이지를 시작 페이지로 설정하겠다.*/
        if(page.getTotalPages() == 0 && endPage == 0) {
            endPage = startPage;
        }

        /* 참고. 계산을 한 정보들을 반환 */
        return new PagingButton(currentPage, startPage, endPage);

    }

    /* 예시) 페이지 15개 현재 페이지가 2페이지면, 시작 페이지는 1, 현재 페이지는 2, 끝 페이지는 10 */

}
