package com.homework.homework_6_13.repository.b.impl;

import org.springframework.stereotype.Repository;

import com.homework.homework_6_13.repository.b.BookDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BookDAOImpl implements BookDAO {

    @PersistenceContext
    private EntityManager entityManager;

}
