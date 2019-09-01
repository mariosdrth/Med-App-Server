package com.patients.gdpr.repositories;

import com.patients.gdpr.dto.UserRoleSearch;
import com.patients.gdpr.model.QUserRole;
import com.patients.gdpr.model.UserRole;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRoleRepositoryImpl implements UserRoleCustom {
    
    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<UserRole> searchUserRoles(UserRoleSearch userRoleSearch) {
        JPAQuery<UserRole> query = new JPAQuery(em);
        QUserRole qUserRole= QUserRole.userRole;
        BooleanBuilder builder = new BooleanBuilder();
        OrderSpecifier orderSpecifier;

        if (userRoleSearch.getDescription() != null) {
            builder.and(qUserRole.description.containsIgnoreCase(userRoleSearch.getDescription()));
        }

        if (userRoleSearch.isAsc()) {
            orderSpecifier = new OrderSpecifier(Order.ASC, Expressions.path(Object.class, qUserRole, userRoleSearch
                    .getOrder()));
        } else {
            orderSpecifier = new OrderSpecifier(Order.DESC, Expressions.path(Object.class, qUserRole, userRoleSearch
                    .getOrder()));
        }

        return query.from(qUserRole)
                .where(builder)
                .orderBy(orderSpecifier)
                .offset((userRoleSearch.getPage() - 1) * userRoleSearch.getSize())
                .limit(userRoleSearch.getSize())
                .fetch();
        
    }
    
}
