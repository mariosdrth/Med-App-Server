package com.patients.gdpr.services;

import com.patients.gdpr.dto.EmailSenderDTO;
import com.patients.gdpr.model.User;
import com.patients.gdpr.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class EmailSenderService {
    
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    public UserService userService;
    @Autowired
    public UserRepository userRepository;
    
    public void sendSimpleMessage(EmailSenderDTO emailSenderDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailSenderDTO.getReceiver());
        message.setSubject(emailSenderDTO.getSubject());
        if (emailSenderDTO.getSenderName() == null) {
            emailSenderDTO.setSenderName(" - ");
        }
        if (emailSenderDTO.getSenderEmail() == null) {
            emailSenderDTO.setSenderEmail(" - ");
        }
        String body;
        if (emailSenderDTO.getLanguage() == null || !emailSenderDTO.getLanguage().equalsIgnoreCase("gr")) {
            body = "Email from " + emailSenderDTO.getSenderName() +". \n\n" + "Contact email address: " +
                    emailSenderDTO.getSenderEmail() + ". \n\n" + "Message: \n" + emailSenderDTO.getMessage();
        } else {
            body = "Ηλεκτρονικό μήνυμα από " + emailSenderDTO.getSenderName() +". \n\n" + "Διεύθυνση email: " +
                    emailSenderDTO.getSenderEmail() + ". \n\n" + "Μήνυμα: \n" + emailSenderDTO.getMessage();
        }
        message.setText(body);
        emailSender.send(message);
    }
    
    public EmailSenderDTO sendResetPassEmail(EmailSenderDTO emailSenderDTO) {
        if (userService.checkForEntity(userRepository.findDistinctByUserName(emailSenderDTO.getUserName()).getId()).isPresent()) {
            User userToChange = userRepository.findDistinctByUserName(emailSenderDTO.getUserName());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailSenderDTO.getReceiver());
            String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]}\\|;:<>/?";
            char[] possibleCharacters = (allCharacters).toCharArray();
            String randomStr = RandomStringUtils.random(8, 0, possibleCharacters.length - 1, false, false,
                    possibleCharacters, new SecureRandom());
            String subject;
            String body;
            if (emailSenderDTO.getLanguage() == null || !emailSenderDTO.getLanguage().equalsIgnoreCase("gr")) {
                subject = "Reset Password";
                if (!userToChange.getUserName().equals("guest")) {
                    body = "Your new password is " + randomStr + ". It is recommended to change this password immediately" +
                            " after next connection.";
                } else {
                    body = "Your new password would be " + randomStr + ". \nSince this is the guest user password " +
                            "will remain the same (guest).";
                }
            } else {
                subject = "Επαναφορά Κωδικού";
                if (!userToChange.getUserName().equals("guest")) {
                    body = "Ο νέος σας κωδικός είναι " + randomStr + ". Συνιστάται να αλλάξετε αυτόν τον κωδικό αμέσως " +
                            "μετά την επόμενη σύνδεση.";
                } else {
                    body = "Ο νέος σας κωδικός θα ήταν " + randomStr + ". \nΕπειδή πρόκειται για τον χρήστη guest ο " +
                            "κωδικός θα παραμείνει ο ίδιος (guest).";
                }
            }
            message.setSubject(subject);
            message.setText(body);
            emailSender.send(message);
            if (!userToChange.getUserName().equals("guest")) {
                userToChange.setPassword(randomStr);
                userRepository.save(userToChange);
            }
            return emailSenderDTO;
        } else {
            return null;
        }
    }
}
