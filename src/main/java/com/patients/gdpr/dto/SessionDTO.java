package com.patients.gdpr.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.patients.gdpr.model.Session;

import java.math.BigInteger;
import java.time.LocalDate;

public class SessionDTO {
  
    private BigInteger id;
    private BigInteger patientId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate sessionDate;
    private String sessionId;
    private String comments;
    private PatientForSessionDTO patientForSessionDTO;
    private long count;
    private String patientName;
    
    public SessionDTO() {
    }
    
    public SessionDTO(Session session) {
        this.id = session.getId();
        this.patientId = session.getPatientId();
        this.sessionDate = session.getSessionDate();
        this.sessionId = session.getSessionId();
        this.comments = session.getComments();
        this.patientName = session.getPatientName();
    }
    
    public BigInteger getId() {
    return id;
  }
  
    public void setId(BigInteger id) {
    this.id = id;
    }
    
    public BigInteger getPatientId() {
    return patientId;
    }
    
    public void setPatientId(BigInteger patientId) {
    this.patientId = patientId;
    }
    
    public LocalDate getSessionDate() {
    return sessionDate;
    }
    
    public void setSessionDate(LocalDate sessionDate) {
    this.sessionDate = sessionDate;
    }
    
    public String getSessionId() {
    return sessionId;
    }
    
    public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
    }
    
    public String getComments() {
    return comments;
    }
    
    public void setComments(String comments) {
    this.comments = comments;
    }
    
    public long getCount() {
        return count;
    }
    
    public void setCount(long count) {
        this.count = count;
    }
    
    public PatientForSessionDTO getPatientForSessionDTO() {
        return patientForSessionDTO;
    }
    
    public void setPatientForSessionDTO(PatientForSessionDTO patientForSessionDTO) {
        this.patientForSessionDTO = patientForSessionDTO;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
