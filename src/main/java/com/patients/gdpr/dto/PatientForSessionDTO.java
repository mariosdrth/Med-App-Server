package com.patients.gdpr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patients.gdpr.model.Patient;

import java.math.BigInteger;
import java.time.LocalDate;

public class PatientForSessionDTO {
    
    private BigInteger id;
    private String name;
    private String surname;
    private String fatherName;
    private String motherName;
    private int sex;
    private String afm;
    private String amka;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String sexForDTO;
    private long count;
    private String tel;
    private String cell;
    private String email;
    private String address;
    private String comments;
    private String nameSurname;
    
    public PatientForSessionDTO() {
    }
    
    public PatientForSessionDTO (Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.surname = patient.getSurname();
        this.fatherName = patient.getFatherName();
        this.motherName = patient.getMotherName();
        this.sex = patient.getSex();
        if (patient.getSex() == 1) {
            this.sexForDTO = "Male";
        } else if (patient.getSex() == 2) {
            this.sexForDTO = "Female";
        }
        this.afm = patient.getAfm();
        this.amka = patient.getAmka();
        this.birthDate = patient.getBirthDate();
        this.tel = patient.getTel();
        this.cell = patient.getCell();
        this.email = patient.getEmail();
        this.address = patient.getAddress();
        this.comments = patient.getComments();
        this.nameSurname = patient.getSurname() + " " + patient.getName();
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
    
    public String getSexForDTO() {
        return sexForDTO;
    }
    
    public void setSexForDTO(String sexForDTO) {
        this.sexForDTO = sexForDTO;
    }
    
    public long getCount() {
        return count;
    }
    
    public void setCount(long count) {
        this.count = count;
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
    
    public String getNameSurname() {
        return nameSurname;
    }
    
    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }
}
