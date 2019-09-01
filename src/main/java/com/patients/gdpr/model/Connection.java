package com.patients.gdpr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patients.gdpr.dto.ConnectionDTO;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "connections")
public class Connection {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "n_id")
    private BigInteger id;
    @Column(name = "v_ip")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_ip, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String ip;
    @Column(name = "v_con_date")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_con_date, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String conDate;
    @Column(name = "v_country")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_country, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String country;
    @Column(name = "v_is_mobile")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_is_mobile, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String isMobile;
    @Column(name = "v_coordinates")
    @ColumnTransformer(read = "CAST(AES_DECRYPT(v_coordinates, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')) AS CHAR(255))",
            write = "AES_ENCRYPT(?, UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2'))")
    private String coordinates;
    
    public Connection() {
    }
    
    public Connection(ConnectionDTO connectionDTO) {
        this.id = connectionDTO.getId();
        this.ip = connectionDTO.getIp();
        this.conDate = connectionDTO.getConDate();
        this.country = connectionDTO.getCountry();
        this.isMobile = connectionDTO.getIsMobile();
        this.coordinates = connectionDTO.getCoordinates();
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
