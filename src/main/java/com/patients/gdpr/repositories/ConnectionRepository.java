package com.patients.gdpr.repositories;

import com.patients.gdpr.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, BigInteger> {
    
    List<Connection> getAllByIp(String ip);
    
}
