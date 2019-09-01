package com.patients.gdpr.repositories;

import com.patients.gdpr.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, BigInteger>, UserRoleCustom {
}
