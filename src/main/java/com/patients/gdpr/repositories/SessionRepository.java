package com.patients.gdpr.repositories;

import com.patients.gdpr.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, BigInteger>, SessionCustom {
    
    List<Session> findAllByPatientId(BigInteger id);
    
}
