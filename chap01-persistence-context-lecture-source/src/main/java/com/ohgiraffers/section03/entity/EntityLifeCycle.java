package com.ohgiraffers.section03.entity;


import jakarta.persistence.EntityManager;

public class EntityLifeCycle {

    private EntityManager manager;
    public EntityManager getManagerInstance() { return manager; }


    public Menu findMenuByCode(int menuCode) {

        manager = EntityManagerGenerator.getManagerInstance();      // manager 사용해 주기위해서 -generator 만들어주고 instance 생성
        return manager.find(Menu.class, menuCode);

    }
}
