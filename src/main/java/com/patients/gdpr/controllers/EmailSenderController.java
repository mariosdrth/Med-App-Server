package com.patients.gdpr.controllers;

import com.patients.gdpr.dto.EmailSenderDTO;
import com.patients.gdpr.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/send-email")
public class EmailSenderController {
    
    @Autowired
    EmailSenderService emailSenderService;
    
    @PostMapping
    @ResponseBody
    public void sendEmail(@RequestBody EmailSenderDTO emailSenderDTO) {
        emailSenderService.sendSimpleMessage(emailSenderDTO);
    }
    
    @PostMapping(value = "/reset-password")
    @ResponseBody
    public EmailSenderDTO sendResetPassEmail(@RequestBody EmailSenderDTO emailSenderDTO) {
        return emailSenderService.sendResetPassEmail(emailSenderDTO);
    }
    
}
