package com.patients.gdpr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patients.gdpr.dto.UserRoleDTO;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_roles")
public class UserRole {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "n_id")
    private BigInteger id;
    @Column(name = "v_description")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_description, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String description;
    @OneToMany(mappedBy = "userRole")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> users;
    
    public UserRole() {
    }
    
    public UserRole(UserRoleDTO userRoleDTO) {
        this.id = userRoleDTO.getId();
        this.description = userRoleDTO.getDescription();
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
