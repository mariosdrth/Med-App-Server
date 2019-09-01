package com.patients.gdpr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patients.gdpr.dto.PatientDTO;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "patients")
public class Patient {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "n_id")
    private BigInteger id;
    @Column(name = "v_name")
    //@Convert(converter = JPACryptoConverter.class)
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_name, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String name;
    @Column(name = "v_surname")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_surname, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String surname;
    @Column(name = "v_father_name")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_father_name, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String fatherName;
    @Column(name = "v_mother_name")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_mother_name, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String motherName;
    @Column(name = "n_sex")
    private int sex;
    @Column(name = "v_afm")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_afm, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String afm;
    @Column(name = "v_amka")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_amka, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String amka;
    @Column(name = "v_birth_date")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_birth_date, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS DATE)",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @Column(name = "v_tel")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_tel, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String tel;
    @Column(name = "v_cell")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_cell, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String cell;
    @Column(name = "v_email")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_email, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String email;
    @Column(name = "v_address")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_address, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String address;
    @Column(name = "v_comments")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_comments, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String comments;
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REMOVE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Session> sessions;
    
    public Patient() {
    }
    
    public Patient(PatientDTO patientDTO) {
        this.id = patientDTO.getId();
        this.name = patientDTO.getName();
        this.surname = patientDTO.getSurname();
        this.fatherName = patientDTO.getFatherName();
        this.motherName = patientDTO.getMotherName();
        this.sex = patientDTO.getSex();
        this.afm = patientDTO.getAfm();
        this.amka = patientDTO.getAmka();
        this.birthDate = patientDTO.getBirthDate();
        this.tel = patientDTO.getTel();
        this.cell = patientDTO.getCell();
        this.email = patientDTO.getEmail();
        this.address = patientDTO.getAddress();
        this.comments = patientDTO.getComments();
        if (patientDTO.getSessionDTOS() != null) {
            this.sessions = new ArrayList<>();
            patientDTO.getSessionDTOS().forEach(sessionDTO -> sessions.add(new Session(sessionDTO)));
        } else {
            this.sessions = null;
        }
    }
    
    public BigInteger getId() {
        return id;
    }
    
    public void setId(BigInteger id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getFatherName() {
        return fatherName;
    }
    
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    
    public String getMotherName() {
        return motherName;
    }
    
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
    
    public int getSex() {
        return sex;
    }
    
    public void setSex(int sex) {
        this.sex = sex;
    }
    
    public String getAfm() {
        return afm;
    }
    
    public void setAfm(String afm) {
        this.afm = afm;
    }
    
    public String getAmka() {
        return amka;
    }
    
    public void setAmka(String amka) {
        this.amka = amka;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public String getCell() {
        return cell;
    }
    
    public void setCell(String cell) {
        this.cell = cell;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public List<Session> getSessions() {
        return sessions;
    }
    
    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
