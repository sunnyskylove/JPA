package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class EntityManagerCRUDTests {

    private EntityManagerCRUD crud;

    @BeforeEach             // 각각의 단위클래스들이 실행되기 전마다 인스턴스를 생성해줄 것임!
    void initManager() {
        this.crud = new EntityManagerCRUD();
    }

    @AfterEach
    void rollback() {
        EntityTransaction transaction = crud.getManagerInstance().getTransaction();     // .getTransaction 기능하나 끝났을때 처리할 수 있는 transaction을 만들어 줌
        transaction.rollback();             // 하나의 단위테스트 끝날때마다 롤백 해줄 것임!!
    }

    @DisplayName("메뉴 코드로 메뉴 조회 테스트")
    /* 필기.
    *   테스트 시에 여러 값들을 이용해서 검증을 진행해야 하는 경우에 경우의 수 만큼 테스트 메소드를
    *   작성해야 한다.
    *   @ParameterizedTest 어노테이션을 붙이게 되면 테스트 메소드에 매개변수를 선언할 수 있다.
    *   (일반적인 테스트 메소드는 매개변수 사용 불가)
    *   파라미터로 전달할 값의 목록 만큼 반복적으로 테스트 메소드를 실행하며 준비 된 값 목록을 전달하여
    *   테스트를 실행할 수 있다. --> given 을 대체할 수 있다. (비교할 조건을 그냥 걸어두면 됨. 굳이 줄 필요가 없다.)
    * */
    @ParameterizedTest
    /* 필기. 여러 개의 테스트 전용 파라미터 전달. 쉼표(,) 로 값을 구분할 수 있다. */
    @CsvSource({"1,1", "2,2", "3,3"})       // 매개변수로 2가지(위,아래 차례대로~)를 3번에 걸쳐 전달 할 것이다~~
    void testFindMethodByMenuCode(int menuCode, int expected) {

        // when
        Menu foundMenu = crud.findMenubyMenuCode(menuCode);

        // then
        Assertions.assertEquals(expected, foundMenu.getMenuCode());
    }

    private static Stream<Arguments> newMenu() {           // Arguments 전달인자 어떤 것이든 받겠다.
            return Stream.of(
              Arguments.of(
                      "신메뉴",
                      20000,
                      4,
                      "Y"
              )
            );
    }

    @DisplayName("새로운 메뉴 추가 테스트")
    @ParameterizedTest
    @MethodSource("newMenu")
    void testRegist(String menuName, int menuPrice, int categoryCode, String orderableStatus) {

        // when
        Menu newMenu = new Menu(menuName, menuPrice, categoryCode, orderableStatus); // Menu클래스에는 ()안 5개가 있었는데, 맨앞 menuCode 매개변수 한개 지우면서, 개수 같아지면서 여기 오류 없어졌다.
        Long count = crud.saveAndReturnAllCount(newMenu);

        //then
        Assertions.assertEquals(22, count);         // sql와 비교해가면서 보기

    }

    @DisplayName("메뉴 이름 수정 테스트")
    @ParameterizedTest
    @CsvSource("1, 변경된 메뉴")
    void testModifyMenuName(int menuCode, String menuName) {


        // when
        Menu modifyMenu = crud.modifyMenuName(menuCode, menuName);

        // then
        Assertions.assertEquals(menuName, modifyMenu.getMenuName());

    }

    @DisplayName("메뉴 삭제 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void testRemoveMenu(int menuCode) {

        Long count = crud.removeAndReturnAllCount(menuCode);

        Assertions.assertEquals(24, count);

    }

}
