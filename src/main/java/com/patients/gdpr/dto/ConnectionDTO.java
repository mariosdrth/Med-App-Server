package com.patients.gdpr.dto;

import com.patients.gdpr.model.Connection;

import java.math.BigInteger;

public class ConnectionDTO {
    
    private BigInteger id;
    private String ip;
    private String conDate;
    private String country;
    private String isMobile;
    private String coordinates;
    
    public ConnectionDTO() {
    }
    
    public ConnectionDTO(Connection connection) {
        this.id = connection.getId();
        this.ip = connection.getIp();
        this.conDate = connection.getConDate();
        this.country = connection.getCountry();
        this.isMobile = connection.getIsMobile();
        this.coordinates = connection.getCoordinates();
    }
    
    public BigInteger getId() {
        return id;
    }
    
    public void setId(BigInteger id) {
        this.id = id;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getConDate() {
        return conDate;
    }
    
    public void setConDate(String conDate) {
        this.conDate = conDate;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getIsMobile() {
        return isMobile;
    }
    
    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }
    
    public String getCoordinates() {
        return coordinates;
    }
    
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
