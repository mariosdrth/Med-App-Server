package com.patients.gdpr.repositories;

import com.patients.gdpr.dto.UserSearch;
import com.patients.gdpr.model.QUser;
import com.patients.gdpr.model.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRepositoryImpl implements UserCustom {
    
    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<User> searchUsers(UserSearch userSearch) {
        JPAQuery<User> query = new JPAQuery(em);
        QUser qUser= QUser.user;
        BooleanBuilder builder = new BooleanBuilder();
        OrderSpecifier orderSpecifier;

        if (userSearch.getUserName() != null) {
            builder.and(qUser.userName.containsIgnoreCase(userSearch.getUserName()));
        }

        if (userSearch.getName() != null) {
            builder.and(qUser.name.containsIgnoreCase(userSearch.getName()));
        }

        if (userSearch.getSurname() != null) {
            builder.and(qUser.surname.containsIgnoreCase(userSearch.getSurname()));
        }

        if (userSearch.getUserRole() != null) {
            builder.and(qUser.userRole.eq(userSearch.getUserRole()));
        }

        if (userSearch.getEmail() != null) {
            builder.and(qUser.email.containsIgnoreCase(userSearch.getEmail()));
        }

        if (userSearch.isAsc()) {
            orderSpecifier = new OrderSpecifier(Order.ASC, Expressions.path(Object.class, qUser, userSearch
                    .getOrder()));
        } else {
            orderSpecifier = new OrderSpecifier(Order.DESC, Expressions.path(Object.class, qUser, userSearch
                    .getOrder()));
        }

        return query.from(qUser)
                .where(builder)
                .orderBy(orderSpecifier)
                .offset((userSearch.getPage() - 1) * userSearch.getSize())
                .limit(userSearch.getSize())
                .fetch();
    }
    
}
