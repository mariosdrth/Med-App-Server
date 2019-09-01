package com.patients.gdpr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patients.gdpr.dto.UserDTO;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "n_id")
    private BigInteger id;
    @Column(name = "v_username")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_username, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String userName;
    @Column(name = "v_password")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_password, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String password;
    @Column(name = "v_name")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_name, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String name;
    @Column(name = "v_surname")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_surname, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String surname;
    @Column(name = "v_email")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_email, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String email;
    @Column(name = "v_tel")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_tel, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String tel;
    @Column(name = "n_user_role_id")
    private BigInteger userRoleId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="n_user_role_id", nullable=false, insertable=false, updatable=false)
    private UserRole userRole;
    
    public User() {
    }
    
    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.userName = userDTO.getUserName();
        this.password = userDTO.getPassword();
        this.name = userDTO.getName();
        this.surname = userDTO.getSurname();
        this.userRoleId = userDTO.getUserRoleId();
        this.userRole = userDTO.getUserRole();
        this.email = userDTO.getEmail();
        this.tel = userDTO.getTel();
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
