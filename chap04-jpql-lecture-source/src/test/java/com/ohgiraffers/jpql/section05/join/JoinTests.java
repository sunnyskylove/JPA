package com.ohgiraffers.jpql.section05.join;

import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JoinTests {

  /* 필기.
  *     1. 일반 조인 : 일반적인 SQL 조인 (inner 조인, out(left, right) 조인, 컬렉션(1대 다) 조인)
  *     2. 패치 조인 : JPQL 에서 성능 최적화를 위해 제공하는 기능으로 연관된 엔티티나 컬렉션을 한번에 조회한다.
  *         -> 지연로딩(Lazy)이 아닌 즉시로딩(Eager)를 수행하며 join fetch 명령어를 이용한다.
  * */

    @Autowired
    private JoinRepository repository;

    @DisplayName("내부 조인(inner)을 이용한 조회 테스트")
    @Test
    void testInnnerJoin() {
        List<Menu> menuList = repository.selectInnerJoin();

        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);

    }

    @DisplayName("외부 조인을 이용한 조회 테스트")
    @Test
    void testOuterJoin() {

        List<Object[]> menuList= repository.selectOutterJoin();

        Assertions.assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for(Object column : row) {
                        System.out.println(column + " ");

                    }
                    System.out.println();
                }
        );

    }

    @DisplayName("컬렉션 조인을 이용한 조회 테스트")
    @Test
    void testCollectionJoin() {
        List<Object[]> categoryList = repository.selectCollectionJoin();

        Assertions.assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for(Object column : row) {
                        System.out.println(column + " ");

                    }
                    System.out.println();
                }
        );

    }

    @DisplayName("패치 조인을 이용한 조회 테스트")
    @Test
    void testFetchJoin(){
        List<Menu> menuList = repository.selectFetchJoin();

        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);

    }
}
