package com.patients.gdpr.dto;

public class BaseSearch {
    
    private int page;
    private boolean asc;
    private String order;
    private int size;
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public boolean isAsc() {
        return asc;
    }
    
    public void setAsc(boolean asc) {
        this.asc = asc;
    }
    
    public String getOrder() {
        return order;
    }
    
    public void setOrder(String order) {
        this.order = order;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
}