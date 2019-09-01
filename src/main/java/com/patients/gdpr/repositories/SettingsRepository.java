package com.patients.gdpr.repositories;

import com.patients.gdpr.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, BigInteger> {
    
    Settings getDistinctByUserId(BigInteger userId);
    
}
