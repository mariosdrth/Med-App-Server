package com.patients.gdpr.dto;

public class MailDTO {
    
    private String subject;
    private String body;
    private String receiver;
    
    public MailDTO(String subject, String body, String receiver) {
        this.subject = subject;
        this.body = body;
        this.receiver = receiver;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public String getBody() {
        return body;
    }
    
    public String getReceiver() {
        return receiver;
    }

}
