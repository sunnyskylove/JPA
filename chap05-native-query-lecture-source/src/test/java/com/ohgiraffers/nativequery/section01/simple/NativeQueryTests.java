package com.ohgiraffers.nativequery.section01.simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class NativeQueryTests {

    /* 필기.
     *   Native Query 란 Mysql 작성했던 쿼리문을 그대로 사용하는 것을 의미한다.
     *   이를 사용하게 된다면 ORM 의 기능을 이용하면서, SQL 쿼리를 사용할 수 있어서
     *   더욱 강력하게 데이터베이스에 접근할 수 있게 된다.
     *   따라서 복잡한 쿼리를 작성할 때나, 특정한 데이터베이스에서만 사용이 가능한
     *   기능을 사용해야 할 때 등에 Native Query 를 사용한다.
     *  */

    /* 필기.
     *   1. 결과 타입 정의가 가능할 때
     *   2. 결과 타입을 정의할 수 없을 때
     *   3. 결과 매핑 사용
     * */

    @PersistenceContext
    private EntityManager manager;

    /* 1. 결과 타입을 정의한 경우 */
    /* 필기.
     *   모든 컬럼값을 매핑하는 경우에만 타입을 특정할 수 있다.
     *   일부 컬럼만 조회를 하고 싶다 ? -> Object[], 스칼라 값을 별로 담을
     *   클래스를 정의해서 사용해야 한다.
     *  */
    @DisplayName("결과 타입을 정의한 Native Query 사용해보기")
    @Test
    @Transactional
    void testNativeQueryByResultType() {

        //given
        int menuCode = 15;

        //when
        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status" +
                " FROM tbl_menu" +
                " WHERE menu_code = ?";

        Query nativeQuery = manager.createNativeQuery(query, Menu.class).setParameter(1, menuCode);
        Menu foundMenu = (Menu) nativeQuery.getSingleResult();

        Assertions.assertNotNull(foundMenu); // 결과 담겼니?
        Assertions.assertTrue(manager.contains(foundMenu));
        System.out.println("foundMenu = " + foundMenu);

    }

    @DisplayName("결과 타입을 지정할 수 없는 Native Query 테스트")
    @Test
    void testNativeQueryNoReuslt() {

        String query = "SELECT menu_name, menu_price FROM tbl_menu";
        List<Object[]> menuList = manager.createNativeQuery(query).getResultList();

        Assertions.assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for(Object col : row) {
                        System.out.print(col + " ");
                    }
                    System.out.println();
                }
        );

    }


}