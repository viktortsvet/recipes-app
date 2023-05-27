package com.viktor.recipebackend.services;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class QueryService {
    @PersistenceContext
    private EntityManager entityManager;

    public enum Bounds {
        NO_BOUNDS, BOUND_BOTH,
        BOUND_FROM, BOUND_TO
    }

    private <T> Query createQuery(@Nonnull String sql, @Nullable Class<T> returnClass) {
        Query query;
        if (returnClass != null) {
            query = entityManager.createNativeQuery(sql, returnClass);
        } else {
            query = entityManager.createNativeQuery(sql);
        }
        return query;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> executeSql(@Nonnull String sql, @Nullable Class<T> returnClass) {
        Query query = createQuery(sql, returnClass);
        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> executeSql(@NonNull String sql, Class<T> returnClass, Map<String, Object> params,
                                  Bounds bounds, Integer start, Integer pageSize) {
        Query query = createQuery(sql, returnClass);
        if (params != null && !params.isEmpty()) {
            setQueryParams(query, params);
        }
        if (bounds != null && start != null && pageSize != null) {
            setPaginationParams(query, bounds, start, pageSize);
        }
        return (List<T>) query.getResultList();
    }

    public long executeSqlSingleResult(String sqlCount, Map<String, Object> params) {
        Query query = createQuery(sqlCount, null);
        setQueryParams(query, params);
        return (long) query.getSingleResult();
    }

    private void setPaginationParams(Query query, Bounds bounds, Integer start, Integer pageSize) {
        switch (bounds) {
            case BOUND_FROM -> query.setFirstResult(start);
            case BOUND_TO -> query.setMaxResults(pageSize);
            case BOUND_BOTH -> {
                query.setFirstResult(start);
                query.setMaxResults(pageSize);
            }
            case NO_BOUNDS -> {}
        }
    }

    private void setQueryParams(Query query, Map<String, Object> params) {
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            query.setParameter(key, params.get(key));
        }
    }

}