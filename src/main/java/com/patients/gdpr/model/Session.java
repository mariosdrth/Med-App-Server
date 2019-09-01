package com.patients.gdpr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patients.gdpr.dto.SessionDTO;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "sessions")
public class Session {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "n_id")
    private BigInteger id;
    @Column(name = "n_patient_id")
    private BigInteger patientId;
    @Column(name = "v_session_date")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_session_date, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS DATE)",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate sessionDate;
    @Column(name = "v_session_id")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_session_id, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String sessionId;
    @Column(name = "v_comments")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_comments, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String comments;
    @Column(name = "v_patient_name")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_patient_name, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String patientName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="n_patient_id", insertable=false, updatable=false)
    private Patient patient;
    
    public Session() {
    }
    
    public Session(SessionDTO sessionDTO) {
        this.id = sessionDTO.getId();
        this.patientId = sessionDTO.getPatientId();
        this.sessionDate = sessionDTO.getSessionDate();
        this.sessionId = sessionDTO.getSessionId();
        this.comments = sessionDTO.getComments();
        this.patientName = sessionDTO.getPatientName();
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
    
    public String getPatientName() {
        return patientName;
    }
    
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
