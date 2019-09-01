package com.patients.gdpr.services;

import com.patients.gdpr.dto.PatientForSessionDTO;
import com.patients.gdpr.dto.SessionDTO;
import com.patients.gdpr.dto.SessionSearch;
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
public class SessionService {
    
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private PatientRepository patientRepository;
    
    @SuppressWarnings(value = "Duplicates")
    public List<SessionDTO> searchSessions(SessionSearch sessionSearch) {
        List<Session> sessionList = sessionRepository.searchSessions(sessionSearch);
        List<Session> sessionsNoLimit = sessionRepository.searchSessionsNoLimit(sessionSearch);
        List<SessionDTO> sessionDTOList = new ArrayList<>();
        for (Session session : sessionList) {
            SessionDTO sessionDTO = new SessionDTO(session);
            Patient patient = patientRepository.getOne(session.getPatientId());
            sessionDTO.setPatientForSessionDTO(new PatientForSessionDTO(patient));
            sessionDTO.setCount(sessionsNoLimit.size());
            sessionDTOList.add(sessionDTO);
        }
        return sessionDTOList;
    }
    
    @SuppressWarnings(value = "Duplicates")
    public List<SessionDTO> searchSessionsAll(SessionSearch sessionSearch) {
        List<Session> sessionsNoLimit = sessionRepository.searchSessionsNoLimit(sessionSearch);
        List<SessionDTO> sessionDTOList = new ArrayList<>();
        for (Session session : sessionsNoLimit) {
            SessionDTO sessionDTO = new SessionDTO(session);
            Patient patient = patientRepository.getOne(session.getPatientId());
            sessionDTO.setPatientForSessionDTO(new PatientForSessionDTO(patient));
            sessionDTO.setCount(sessionsNoLimit.size());
            sessionDTOList.add(sessionDTO);
        }
        return sessionDTOList;
    }
    
    public Long getSizeOfSessions() {
        return sessionRepository.count();
    }
    
    public List<SessionDTO> createEntity(List<SessionDTO> sessionDTOList) {
        for (SessionDTO sessionDTO : sessionDTOList) {
            Patient patient = patientRepository.getOne(sessionDTO.getPatientId());
            sessionDTO.setPatientName(patient.getSurname() + " " + patient.getName());
            Session session = sessionRepository.save(DTOToEntity(sessionDTO));
            sessionDTO.setId(session.getId());
            sessionDTO.setPatientForSessionDTO(new PatientForSessionDTO(patient));
        }
        return sessionDTOList;
    }
    
    public SessionDTO updateEntity(BigInteger id, SessionDTO sessionDTO) {
        sessionDTO.setId(id);
        return entityToDTO(sessionRepository.save(DTOToEntity(sessionDTO)));
    }
    
    public SessionDTO showDetails(BigInteger id) {
        return entityToDTO(sessionRepository.getOne(id));
    }
    
    public void deleteEntity(BigInteger id) {
        sessionRepository.delete(sessionRepository.getOne(id));
    }
    
    public Optional<Session> checkForEntity(BigInteger id) {
        Optional<Session> checkSession = sessionRepository.findById(id);
        return checkSession;
    }
    
    public SessionDTO entityToDTO(Session session) {
        return new SessionDTO(session);
    }
    
    public Session DTOToEntity(SessionDTO sessionDTO) {
        return new Session(sessionDTO);
    }
    
}
