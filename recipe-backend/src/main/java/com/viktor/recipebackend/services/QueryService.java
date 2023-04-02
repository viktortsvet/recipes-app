package com.viktor.recipebackend.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    public <T> List<T> executeSql(@NonNull String sql, @NonNull Map<String, Object> params) {
        Query query = entityManager.createQuery(sql);
        Collection<String> keys = params.keySet();
        for (String key : keys) {
            query.setParameter(key, params.get(key));
        }
        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> executeSql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> executeHql(String hql, Class<T> returnClass) {
        Query query = entityManager.createQuery(hql, returnClass);
        return (List<T>) query.getResultList();
    }

}