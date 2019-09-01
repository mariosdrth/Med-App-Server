package com.patients.gdpr.dto;

import com.patients.gdpr.model.User;
import com.patients.gdpr.model.UserRole;

import java.math.BigInteger;

public class UserDTO {
    
    private BigInteger id;
    private String userName;
    private String password;
    private String name;
    private String surname;
    private BigInteger userRoleId;
    private UserRole userRole;
    private String email;
    private String tel;
    
    public UserDTO() {
    }
    
    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        //this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.userRoleId = user.getUserRoleId();
        this.userRole = user.getUserRole();
        this.email = user.getEmail();
        this.tel = user.getTel();
    }
    
    public BigInteger getId() {
        return id;
    }
    
    public void setId(BigInteger id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
    public BigInteger getUserRoleId() {
        return userRoleId;
    }
    
    public void setUserRoleId(BigInteger userRoleId) {
        this.userRoleId = userRoleId;
    }
    
    public UserRole getUserRole() {
        return userRole;
    }
    
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
}
