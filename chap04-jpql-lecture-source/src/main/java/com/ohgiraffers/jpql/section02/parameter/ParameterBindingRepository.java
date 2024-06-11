package com.ohgiraffers.jpql.section02.parameter;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParameterBindingRepository {

    @PersistenceContext
    private EntityManager manager;


    public List<Menu> selectMenuByBindingName(String menuName) {

        String jpql = "SELECT m FROM section02Menu m WHERE m.menuName = :menuName";

        List<Menu> resultMenuList = manager.createQuery(jpql, Menu.class)
                                            .setParameter("menuName", menuName)         // 이름 기반
                                            .getResultList();

        return resultMenuList;
    }

    public List<Menu> selectMenuByBindingPosition(String menuName) {

        String jpql = "SELECT m FROM section02Menu m WHERE m.menuName = ?1";

        List<Menu> resultMenuList = manager.createQuery(jpql, Menu.class)
                .setParameter(1, menuName)         // 위치 기반
                .getResultList();

        return resultMenuList;
    }
}
