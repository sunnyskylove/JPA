package com.ohgiraffers.associationmapping.section01.manytoone;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManyToOneAssociationTest {

    /* 수업목표. 연관관계 Mapping 에 대해 이해해보자. */

    /* 필기.
    *   Entity 클래스 간의 관계를 매핑하는 것을 의미한다.
    *   -> 이를 통해서 객체를 이용하여 데이터베이스의 테이블 간 관계를 매핑할 수 있다.
    *   [다중성에 의한 분류]
    *   : 연관관계가 있는 객체 관계에서 실제로 연관을 가지는 객체의 수에 따라서 분류되다.
    *   - 1:1 (OneToOne)
    *   - 1:N (OneToMany)
    *   - N:1 (ManyToOne)
    *   - N:N (ManyToMany)
    *   [방향에 따른 분류]
    *   : 테이블의 연관관계는 외래키를 이용한 양방향 연관 관계의 특징을 가진다.
    *   * 다만, 참조에 의한 객체의 연관 관계는 단방향이다.
    *   객체 간의 연관 관계를 양방향으로 만들고 싶은 경우 반대 쪽에서도 필드를 추가해서 참조를 보관하면 된다.
    *   하지만 엄밀하게 말해서는 양방향이 아닌 단방향 관계가 2개로 볼 수 이다.
    * */

    /* 필기.
    *   ManyToOne은 다수의 엔티티가 하나의 엔티티를 참조하는 상황에서 사용된다.
    *   예를 들어 하나의 카테고리가 여러 개의 메뉴를 가질 수 있는 상황에서 메뉴 엔티티가 카테고리 엔티티를 참조하고 있는 것이다.
    *   이 때, Menu 엔티티는 Many, Category 엔티티는 One 이 된다.
    *   연관 관계를 가지는 엔티티를 조회하는 방법은
    *   1. 객체 그래프 탐색(객체 연관 관계를 사용한 조회)(참조 연산자 .을 통해 접근 할 수 있다.)   / .해서 들어갈 수 있는 방식
    *   2. 객체 지향 쿼리(JPQL)
    * */

}

