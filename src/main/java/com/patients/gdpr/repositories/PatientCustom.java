package com.patients.gdpr.repositories;

import com.patients.gdpr.dto.PatientSearch;
import com.patients.gdpr.model.Patient;

import java.util.List;

public interface PatientCustom {
    
    List<Patient> searchPatients(PatientSearch patientSearch);
    List<Patient> searchPatientsNoLimit(PatientSearch patientSearch);
    
}
