package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

//import static sun.management.MemoryNotifInfoCompositeData.getCount;

public class EntityManagerCRUD {

    private EntityManager manager;


    public EntityManager getManagerInstance() {

        return manager;
    }

    public Menu findMenubyMenuCode(int menuCode) {

        manager = EntityManagerGenerator.getManagerInstance();

        return manager.find(Menu.class, menuCode);          // 우리가 sql문 짜는게 아니라 manager가 대신 짜줄 것임!, ()안에 있는 정보를 사용하면서 값 전달하기~ => 찾을건데 (Menu.class를 기반으로, menuCode를 전달할거야.)

    }

    public Long saveAndReturnAllCount(Menu newMenu) {

        manager = EntityManagerGenerator.getManagerInstance();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();        // 직접적으로 연관이 되니깐 transaction을 시작할 것임~~
        manager.persist(newMenu);       // DB와 직접적으로 소통하지않고, persist(가지고 있음) 메소드를 호출해서 여기 persist에다가 등록하는 것임!(전달받은 새로운 메뉴를~)>> 이런걸 매니저에게 전달!
        manager.flush();

        return getCount(manager);


    }

    private Long getCount(EntityManager manager) {

        return manager.createQuery("SELECT COUNT(*) FROM section02Menu", Long.class).getSingleResult();
    }

    public Menu modifyMenuName(int menuCode, String menuName) {

        Menu foundMenu =findMenubyMenuCode(menuCode);       //**메소드의 장점!! 재사용 가능!!(위에 findMenubyMenuCode 만들어놓은거 재사용하기~~)

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        foundMenu.setMenuName(menuName);

        manager.flush();            // 데이터 베이스에 반영해주고~

        return foundMenu;          // 자신을 호출한 곳으로 다시 리턴해줄 것임!
    }
}
