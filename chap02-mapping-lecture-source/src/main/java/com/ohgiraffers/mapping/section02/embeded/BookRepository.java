package com.ohgiraffers.mapping.section02.embeded;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager manager;

    public void save(Book book) {

        manager.persist(book);      // manager 야~ 전달할 책을 전달해줘~~

    }
}
