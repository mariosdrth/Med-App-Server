package com.patients.gdpr.dto;

import com.patients.gdpr.model.Settings;

import java.math.BigInteger;

public class SettingsDTO {
    
    private BigInteger id;
    private int altView;
    private int linear;
    private BigInteger userId;
    private int openOnClick;
    private String headerColor;
    private String themeColor;
    private String sideColor;
    private int theme;
    
    public SettingsDTO() {
    }
    
    public SettingsDTO(Settings settings) {
        this.id = settings.getId();
        this.altView = settings.getAltView();
        this.linear = settings.getLinear();
        this.userId = settings.getUserId();
        this.openOnClick = settings.getOpenOnClick();
        this.headerColor = settings.getHeaderColor();
        this.themeColor = settings.getThemeColor();
        this.sideColor = settings.getSideColor();
        this.theme = settings.getTheme();
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
