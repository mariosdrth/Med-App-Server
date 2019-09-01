package com.patients.gdpr.dto;

import com.patients.gdpr.model.UserRole;

import java.math.BigInteger;
import java.util.List;

public class UserRoleDTO {
    
    private BigInteger id;
    private String description;
    private List<UserDTO> usersDTO;
    
    public UserRoleDTO() {
    }
    
    public UserRoleDTO(UserRole userRole) {
        this.id = userRole.getId();
        this.description = userRole.getDescription();
    }
    
    public BigInteger getId() {
        return id;
    }
    
    public void setId(BigInteger id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
