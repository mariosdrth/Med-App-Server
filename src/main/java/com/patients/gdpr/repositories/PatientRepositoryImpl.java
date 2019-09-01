package com.patients.gdpr.repositories;

import com.patients.gdpr.dto.PatientSearch;
import com.patients.gdpr.model.Patient;
import com.patients.gdpr.model.QPatient;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PatientRepositoryImpl implements PatientCustom{
    
    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Patient> searchPatients(PatientSearch patientSearch) {
        JPAQuery<Patient> query = new JPAQuery(em);
        QPatient qPatient= QPatient.patient;

        return query.from(qPatient)
                .where(prepareBuilder(patientSearch, qPatient))
                .orderBy(prepareOrder(patientSearch, qPatient))
                .offset((patientSearch.getPage() - 1) * patientSearch.getSize())
                .limit(patientSearch.getSize())
                .fetch();
    }
    
    @Override
    public List<Patient> searchPatientsNoLimit(PatientSearch patientSearch) {
        JPAQuery<Patient> query = new JPAQuery(em);
        QPatient qPatient= QPatient.patient;

        return query.from(qPatient)
                .where(prepareBuilder(patientSearch, qPatient))
                .fetch();
    }

    private BooleanBuilder prepareBuilder(PatientSearch patientSearch, QPatient qPatient) {
        BooleanBuilder builder = new BooleanBuilder();

        if (patientSearch.getAfm() != null) {
            builder.and(qPatient.afm.containsIgnoreCase(patientSearch.getAfm()));
        }

        if (patientSearch.getAmka() != null) {
            builder.and(qPatient.amka.containsIgnoreCase(patientSearch.getAmka()));
        }

        if (patientSearch.getSurname() != null) {
            builder.and(qPatient.surname.containsIgnoreCase(patientSearch.getSurname()));
        }

        if (patientSearch.getName() != null) {
            builder.and(qPatient.name.containsIgnoreCase(patientSearch.getName()));
        }

        if (patientSearch.getFatherName() != null) {
            builder.and(qPatient.fatherName.containsIgnoreCase(patientSearch.getFatherName()));
        }

        if (patientSearch.getMotherName() != null) {
            builder.and(qPatient.motherName.containsIgnoreCase(patientSearch.getMotherName()));
        }

        if (patientSearch.getBirthDateFrom() != null) {
            builder.and(qPatient.birthDate.goe(patientSearch.getBirthDateFrom()));
        }

        if (patientSearch.getBirthDateTo() != null) {
            builder.and(qPatient.birthDate.loe(patientSearch.getBirthDateTo()));
        }

        if (patientSearch.getSex() != 0) {
            builder.and(qPatient.sex.eq(patientSearch.getSex()));
        }
    
        if (patientSearch.getCell() != null) {
            builder.and(qPatient.cell.containsIgnoreCase(patientSearch.getCell()));
        }
    
        if (patientSearch.getTel() != null) {
            builder.and(qPatient.tel.containsIgnoreCase(patientSearch.getTel()));
        }
    
        if (patientSearch.getEmail() != null) {
            builder.and(qPatient.email.containsIgnoreCase(patientSearch.getEmail()));
        }
    
        if (patientSearch.getAddress() != null) {
            builder.and(qPatient.address.containsIgnoreCase(patientSearch.getAddress()));
        }
        
        return builder;
    }
    
    @SuppressWarnings("unchecked")
    private OrderSpecifier prepareOrder(PatientSearch patientSearch, QPatient qPatient) {
        OrderSpecifier orderSpecifier;

        if (patientSearch.isAsc()) {
            orderSpecifier = new OrderSpecifier(Order.ASC, Expressions.path(Object.class, qPatient, patientSearch
                    .getOrder()));
        } else {
            orderSpecifier = new OrderSpecifier(Order.DESC, Expressions.path(Object.class, qPatient, patientSearch
                    .getOrder()));
        }

        return orderSpecifier;
    }
}
