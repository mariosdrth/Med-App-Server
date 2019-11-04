package com.patients.gdpr.repositories;

import com.patients.gdpr.dto.PatientForSessionDTO;
import com.patients.gdpr.dto.SessionSearch;
import com.patients.gdpr.model.QSession;
import com.patients.gdpr.model.Session;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SessionRepositoryImpl implements SessionCustom {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Session> searchSessions(SessionSearch sessionSearch) {
        JPAQuery<Session> query = new JPAQuery(em);
        QSession qSession= QSession.session;
    
        if (!sessionSearch.getOrder().equals("") && sessionSearch.getOrder() != null) {
            return query.from(qSession)
                    .where(prepareBuilder(sessionSearch, qSession))
                    .orderBy(prepareOrder(sessionSearch, qSession))
                    .offset((sessionSearch.getPage() - 1) * sessionSearch.getSize())
                    .limit(sessionSearch.getSize())
                    .fetch();
        } else {
            return query.from(qSession)
                    .where(prepareBuilder(sessionSearch, qSession))
                    .offset((sessionSearch.getPage() - 1) * sessionSearch.getSize())
                    .limit(sessionSearch.getSize())
                    .fetch();
        }
    }
    
    @Override
    public List<Session> searchSessionsNoLimit(SessionSearch sessionSearch) {
        JPAQuery<Session> query = new JPAQuery(em);
        QSession qSession= QSession.session;
    
        return query.from(qSession)
                .where(prepareBuilder(sessionSearch, qSession))
                .fetch();
    }
    
    private BooleanBuilder prepareBuilder(SessionSearch sessionSearch, QSession qSession) {
        BooleanBuilder builder = new BooleanBuilder();
        
        if (sessionSearch.getSessionId() != null) {
            builder.and(qSession.sessionId.containsIgnoreCase(sessionSearch.getSessionId()));
        }
        
        if (sessionSearch.getSessionDateFrom() != null) {
            builder.and(qSession.sessionDate.goe(sessionSearch.getSessionDateFrom()));
        }
    
        if (sessionSearch.getSessionDateTo() != null) {
            builder.and(qSession.sessionDate.loe(sessionSearch.getSessionDateTo()));
        }
        
        if (sessionSearch.getPatientId() != null) {
            builder.and(qSession.patientId.eq(sessionSearch.getPatientId()));
        }
        
        if (sessionSearch.getPatientForSessionDTO() != null && sessionSearch.getPatientForSessionDTO().getId() != null) {
            builder.and(qSession.patientId.eq(sessionSearch.getPatientForSessionDTO().getId()));
        }
    
        if (sessionSearch.getPatientForSessionDTOList() != null) {
            for (PatientForSessionDTO patientForSessionDTO : sessionSearch.getPatientForSessionDTOList()) {
                if (patientForSessionDTO.getId() != null) {
                    builder.or(qSession.patientId.eq(patientForSessionDTO.getId()));
                }
            }
        }
        
        return builder;
    }
    
    @SuppressWarnings("unchecked")
    private OrderSpecifier prepareOrder(SessionSearch sessionSearch, QSession qSession) {
        OrderSpecifier orderSpecifier;
        
        if (sessionSearch.isAsc()) {
            if (sessionSearch.getOrder().equals("patientForSessionDTO")) {
                orderSpecifier = new OrderSpecifier(Order.ASC, Expressions.path(Object.class, qSession, "patientName"));
            } else {
                orderSpecifier = new OrderSpecifier(Order.ASC, Expressions.path(Object.class, qSession, sessionSearch
                        .getOrder()));
            }
        } else {
            if (sessionSearch.getOrder().equals("patientForSessionDTO")) {
                orderSpecifier = new OrderSpecifier(Order.DESC, Expressions.path(Object.class, qSession, "patientName"));
            } else {
                orderSpecifier = new OrderSpecifier(Order.DESC, Expressions.path(Object.class, qSession, sessionSearch
                        .getOrder()));
            }
        }
        
        return orderSpecifier;
    }
}
