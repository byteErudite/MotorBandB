package com.vaibhav.parkingReservation.customRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class RootCustomRepository {

    private Integer ZERO = 0;
    @Autowired
    protected EntityManager entityManager;

    public <R> List<R> getQueryResult(final String queryString, final Map<String, Object> paramerters, final Integer page, final Integer size, final Class<R> className) {
        final Query query = entityManager.createQuery(queryString, className);
        return getDataFromQuery(size, page, query, paramerters);
    }

    private <R> List<R> getDataFromQuery(Integer size, Integer page, final Query query, final Map<String, Object> parameters) {
        if (Objects.isNull(page) || Objects.isNull(size)) {

        }
        int initialResults = 0;
        if (page != ZERO) {
            initialResults = (page - 1) * size;
        }
        query.setFirstResult(initialResults);
        query.setMaxResults(size);
        parameters.forEach(query::setParameter);
        return query.getResultList();
    }

    public Long getNativeQueryCount(final String sqlQuery, final Map<String, Object> parameterMap) {
        final Query countQuery = entityManager.createNativeQuery(sqlQuery);
        parameterMap.forEach(countQuery::setParameter);
        return (Long) countQuery.getSingleResult();
    }

    public Long getQueryCount(final String sqlQuery, final Map<String, Object> parameterMap) {
        final Query countQuery = entityManager.createQuery(sqlQuery);
        parameterMap.forEach(countQuery::setParameter);
        return (Long) countQuery.getSingleResult();
    }
}
