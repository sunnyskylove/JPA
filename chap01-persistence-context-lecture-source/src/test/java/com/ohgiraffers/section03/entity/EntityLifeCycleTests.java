package com.ohgiraffers.section03.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class EntityLifeCycleTests {

    private EntityLifeCycle lifeCycle;

    @BeforeEach
    void setUp() {
        this.lifeCycle = new EntityLifeCycle();
    }

    @DisplayName("비영속 테스트")
    @ParameterizedTest          // 매개변수 전달할 것임~~
    @ValueSource(ints = {1, 2})
    void testTransient(int menuCode) {

        Menu foundMenu = lifeCycle.findMenuByCode(menuCode);

        Menu newMenu = new Menu(
                foundMenu.getMenuCode(),
                foundMenu.getMenuName(),
                foundMenu.getMenuPrice(),
                foundMenu.getCategoryCode(),
                foundMenu.getOrderableStatus()
        );

        Assertions.assertNotEquals(foundMenu, newMenu);         // ( , ) 안 서로 비교
        Assertions.assertFalse(lifeCycle.getManagerInstance().contains(newMenu));       // 위에 새로만든 메뉴인 newMenu랑 똑같이 만든것을 가지고 있느냐 묻는것!(안가지고 있으니, 안가지고 있다고 하면 통과!)
//        Assertions.assertFalse(lifeCycle.getManagerInstance().contains(foundMenu));       // 위에 새로만든 메뉴인 foundMenueh 똑같이 만든것을 가지고 있느냐 묻는것!

    }

    @DisplayName("다른 엔티티 매니저가 엔티티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testOtherManager(int menuCode) {

        Menu menu1 = lifeCycle.findMenuByCode(menuCode);
        Menu menu2 = lifeCycle.findMenuByCode(menuCode);

        Assertions.assertNotEquals(menu1, menu2);

    }

    @DisplayName("같은 엔티티 매니저가 관리하는 엔티티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testSameManager(int menuCode) {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        Menu menu1 = manager.find(Menu.class, menuCode);
        Menu menu2 = manager.find(Menu.class, menuCode);

        Assertions.assertEquals(menu1, menu2);

    }

    @DisplayName("준영속화 detach 테스트")
    @ParameterizedTest
    @CsvSource({"11, 1000", "12, 1000"})
    void testDetachEntity(int menuCode, int menuPrice) {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();

        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();

        /* 참고. detach()
         *   특정 엔티티만 준영속 상태(영속성 컨텍스트가 관리하던 엔티티 객체를 관리하지 않는 상태)
         * */

        manager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);

        manager.flush();

        Assertions.assertNotEquals(menuPrice, manager.find(Menu.class, menuCode).getMenuPrice());
        transaction.rollback();

    }

    @DisplayName("준영속화 detach 후 다시 영속화 테스트")
    @ParameterizedTest(name = "[{index}] 준영속화 된 {0} 번 메뉴를 {1}원으로 변경하고 다시 영속화 되는 지 확인")
    @CsvSource({"11, 1000", "12, 1000"})
    void testDetachAndPersist(int menuCode, int menuPrice) {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();

        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();
        manager.detach(foundMenu);      // 준영속 상태
        foundMenu.setMenuPrice(menuPrice);
    /* 필기.
        파라미터로 넘어온 준영속 엔티티 객체의 식별자 값으로 1차 캐시에서 엔티티 객체를 조회한다.
        만약 1차 캐시에 엔티티가 없으면 데이터베이스에서 엔티티를 조회하고 1차 캐시에 저장한다.
        조회한 영속 엔티티 객체에 준영속 상태의 엔티티 객체의 값을 병합한 뒤 영속 엔티티 객체를 반환한다.
        혹은 조회할 수 없는 데이터의 경우 새로 생성해서 병합한다. (save or update)
     */
    manager.merge(foundMenu);
    manager.flush();

        Assertions.assertEquals(menuPrice, manager.find(Menu.class, menuCode).getMenuPrice());
        transaction.rollback();
    }


    // 이건 결과 안나오는게 맞음! 999가 추가가 됨
        @DisplayName("detach 준영속 후 merge 한 데이터 save 테스트")
        @Test
        void testDetachAndMergeSave() {

            EntityManager manager = EntityManagerGenerator.getManagerInstance();
            EntityTransaction transaction = manager.getTransaction();
            Menu foundMenu = manager.find(Menu.class, 20);

            manager.detach(foundMenu);     // foundMenu를 준영속화 시킬 것임(위의 20으로 된 걸 준영속화_뺴다 시켰다).

            transaction.begin();
            foundMenu.setMenuCode(999);
            foundMenu.setMenuName("닭가슴살샐러드");

            manager.merge(foundMenu);
            manager.flush();

            Assertions.assertEquals("닭가슴살샐러드", manager.find(Menu.class, 999).getMenuName());
        }

        @DisplayName("준영속화 clear 테스트")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3})
        void testClearPersistenceContext(int menuCode) {

            EntityManager manager = EntityManagerGenerator.getManagerInstance();
            Menu foundMenu = manager.find(Menu.class, menuCode);

            // clear() : 영속성 컨텍스트를 초기화 -> 영속성 컨텍스트 내의 모든 엔티티를 준영속화 시킨다.
            manager.clear();

            Menu expectedMenu = manager.find(Menu.class, menuCode);
            Assertions.assertNotEquals(expectedMenu, foundMenu);
        }

        @DisplayName("준영속화 close 테스트")
        @ParameterizedTest
        @ValueSource(ints = {1,2,3})
        void closePersistenceContext(int menuCode) {

            EntityManager manager = EntityManagerGenerator.getManagerInstance();

            Menu foundMenu = manager.find(Menu.class, menuCode);

            // close() : 영속성 컨텍스트를 종료한다 -> 영속성 컨텍스트 내 모든 객체를 준영속화 시킨다.
            manager.close();

//        Menu expectedMenu = manager.find(Menu.class, menuCode);
//        Assertions.assertNotEquals(expectedMenu, foundMenu);
            Assertions.assertThrows(            // 여기선 예외가 나는게 정상이다.
                    IllegalStateException.class,
                    () -> manager.find(Menu.class, menuCode)
            );
        }

        @DisplayName("영속성 엔티티 삭제 remove 테스트")
        @ParameterizedTest
        @ValueSource(ints = {1})
        void testRemoveEntity(int menuCode){

            EntityManager manager = EntityManagerGenerator.getManagerInstance();
            EntityTransaction transaction = manager.getTransaction();

            Menu foundMenu = manager.find(Menu.class, menuCode);

            transaction.begin();

        /* 필기.
            remove()
            엔티티를 영속성 컨텍스트 및 데이터베이스에서 삭제한다.
            단, 트랜젝션을 제어하지 않으면, 데이터베이스에 영구 반영되지는 않는다.
            트랜잭션 커밋 or 플러쉬 하는 순간 영속성 컨텍스트에서 관리하는 엔티티 객체가 데이터 베이스에 반영된다.
            */

            manager.remove(foundMenu);        // manager야~ foundMenu 라는 것을 삭제해줘~!!

            manager.flush();

//        Assertions.assertFalse(manager.contains(foundMenu));
            Menu refoundMenu = manager.find(Menu.class, menuCode);
            Assertions.assertEquals(null, refoundMenu);    // remove -> commit 까지 하고 (=flush(반영)) 했었는데 refound가 있는지 확인>> 없다! / 데이터베이스까지 반영한다.

//            transaction.rollback();

        }
    }
