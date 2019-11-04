package com.patients.gdpr.services;

import com.patients.gdpr.dto.PatientDTO;
import com.patients.gdpr.dto.PatientSearch;
import com.patients.gdpr.model.Patient;
import com.patients.gdpr.model.Session;
import com.patients.gdpr.repositories.PatientRepository;
import com.patients.gdpr.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private SessionRepository sessionRepository;
    
    public List<PatientDTO> searchPatients(PatientSearch patientSearch) {
        List<Patient> patientList = patientRepository.searchPatients(patientSearch);
        List<Patient> patientListNoLimit = patientRepository.searchPatientsNoLimit(patientSearch);
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            PatientDTO patientDTO = new PatientDTO(patient);
            patientDTO.setCount(patientListNoLimit.size());
            patientDTOList.add(patientDTO);
        }
        return patientDTOList;
    }
    
    public List<PatientDTO> getAllPatients() {
        List<PatientDTO> patientDTOList = new ArrayList<>();
        patientRepository.findAll().forEach(patient -> patientDTOList.add(new PatientDTO(patient)));
        return patientDTOList;
    }
    
    public Long getSizeOfPatients() {
        return patientRepository.count();
    }
    
    public List<PatientDTO> createEntity(List<PatientDTO> patientDTOList) {
        for (PatientDTO patientDTO : patientDTOList) {
            Patient patient = patientRepository.save(DTOToEntity(patientDTO));
            patientDTO.setId(patient.getId());
            if (patient.getSex() == 1) {
                patientDTO.setSexForDTO("Male");
            } else if (patient.getSex() == 2) {
                patientDTO.setSexForDTO("Female");
            }
        }
        return patientDTOList;
    }
    
    public PatientDTO updateEntity(BigInteger id, PatientDTO patientDTO) {
        Patient patient = patientRepository.getOne(id);
        if (!patient.getName().equals(patientDTO.getName()) || !patient.getSurname().equals(patientDTO.getSurname())) {
            List<Session> sessions = sessionRepository.findAllByPatientId(id);
            if (!sessions.isEmpty()) {
                for (Session session : sessions) {
                    session.setPatientName(patientDTO.getSurname() + " " + patientDTO.getName());
                    sessionRepository.save(session);
                }
            }
        }
        patientDTO.setId(id);
        return entityToDTO(patientRepository.save(DTOToEntity(patientDTO)));
    }
    
    public PatientDTO showDetails(BigInteger id) {
        return entityToDTO(patientRepository.getOne(id));
    }
    
    public void deleteEntity(BigInteger id) {
        patientRepository.delete(patientRepository.getOne(id));
    }
    
    public Optional<Patient> checkForEntity(BigInteger id) {
        return patientRepository.findById(id);
    }
    
    public PatientDTO entityToDTO(Patient patient) {
        return new PatientDTO(patient);
    }
    
    public Patient DTOToEntity(PatientDTO patientDTO) {
        return new Patient(patientDTO);
    }
    
}
