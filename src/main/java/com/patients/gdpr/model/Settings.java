package com.patients.gdpr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patients.gdpr.dto.SettingsDTO;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "settings")
public class Settings {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "n_id")
    private BigInteger id;
    @Column(name = "n_alt_view")
    private int altView;
    @Column(name = "n_linear")
    private int linear;
    @Column(name = "n_user_id")
    private BigInteger userId;
    @Column(name = "n_open_on_click")
    private int openOnClick;
    @Column(name = "v_header_color")
    private String headerColor;
    @Column(name = "v_theme_color")
    private String themeColor;
    @Column(name = "v_side_color")
    private String sideColor;
    @Column(name = "n_theme")
    private int theme;
    
    public Settings() {
    }
    
    public Settings(SettingsDTO settingsDTO) {
        this.id = settingsDTO.getId();
        this.altView = settingsDTO.getAltView();
        this.linear = settingsDTO.getLinear();
        this.userId = settingsDTO.getUserId();
        this.openOnClick = settingsDTO.getOpenOnClick();
        this.headerColor = settingsDTO.getHeaderColor();
        this.themeColor = settingsDTO.getThemeColor();
        this.sideColor = settingsDTO.getSideColor();
        this.theme = settingsDTO.getTheme();
    }
    
    public BigInteger getId() {
        return id;
    }
    
    public void setId(BigInteger id) {
        this.id = id;
    }
    
    public int getAltView() {
        return altView;
    }
    
    public void setAltView(int altView) {
        this.altView = altView;
    }
    
    public int getLinear() {
        return linear;
    }
    
    public void setLinear(int linear) {
        this.linear = linear;
    }
    
    public BigInteger getUserId() {
        return userId;
    }
    
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
    
    public int getOpenOnClick() {
        return openOnClick;
    }
    
    public void setOpenOnClick(int openOnClick) {
        this.openOnClick = openOnClick;
    }
    
    public String getHeaderColor() {
        return headerColor;
    }
    
    public void setHeaderColor(String headerColor) {
        this.headerColor = headerColor;
    }
    
    public String getThemeColor() {
        return themeColor;
    }
    
    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }
    
    public String getSideColor() {
        return sideColor;
    }
    
    public void setSideColor(String sideColor) {
        this.sideColor = sideColor;
    }
    
    public int getTheme() {
        return theme;
    }
    
    public void setTheme(int theme) {
        this.theme = theme;
    }
}
