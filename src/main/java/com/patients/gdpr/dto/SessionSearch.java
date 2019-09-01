package com.patients.gdpr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class SessionSearch extends BaseSearch {
    
    private BigInteger id;
    private BigInteger patientId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate sessionDateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate sessionDateTo;
    private String sessionId;
    private String comments;
    private PatientForSessionDTO patientForSessionDTO;
    private List<PatientForSessionDTO> patientForSessionDTOList;
    private long count;
    private String patientName;
    
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
    
    public LocalDate getSessionDateFrom() {
        return sessionDateFrom;
    }
    
    public void setSessionDateFrom(LocalDate sessionDateFrom) {
        this.sessionDateFrom = sessionDateFrom;
    }
    
    public LocalDate getSessionDateTo() {
        return sessionDateTo;
    }
    
    public void setSessionDateTo(LocalDate sessionDateTo) {
        this.sessionDateTo = sessionDateTo;
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
    
    public PatientForSessionDTO getPatientForSessionDTO() {
        return patientForSessionDTO;
    }
    
    public void setPatientForSessionDTO(PatientForSessionDTO patientForSessionDTO) {
        this.patientForSessionDTO = patientForSessionDTO;
    }
    
    public long getCount() {
        return count;
    }
    
    public void setCount(long count) {
        this.count = count;
    }
    
    public List<PatientForSessionDTO> getPatientForSessionDTOList() {
        return patientForSessionDTOList;
    }
    
    public void setPatientForSessionDTOList(List<PatientForSessionDTO> patientForSessionDTOList) {
        this.patientForSessionDTOList = patientForSessionDTOList;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
