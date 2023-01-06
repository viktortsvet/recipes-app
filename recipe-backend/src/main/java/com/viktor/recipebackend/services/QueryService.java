package com.viktor.recipebackend.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QueryService {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public <T> List<T> executeSql(String sql, Class<T> returnClass) {
        Query query = entityManager.createNativeQuery(sql, returnClass);
        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> executeSql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return (List<T>) query.getResultList();
    }

}