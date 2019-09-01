package com.patients.gdpr.repositories;

import com.patients.gdpr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger>, UserCustom {
    
    User findDistinctByUserName(String username);
    
}
