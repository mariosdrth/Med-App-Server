package com.patients.gdpr.services;

import com.patients.gdpr.dto.EmailSenderDTO;
import com.patients.gdpr.dto.MailDTO;
import com.patients.gdpr.model.User;
import com.patients.gdpr.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class EmailSenderService {
    
    @Autowired
    public UserService userService;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private EmailRestApiService emailRestApiService;

    private static final String GUEST_USER = "guest";
    private static final String DOUBLE_NEW_LINE = "\n";

    public void sendSimpleMessage(EmailSenderDTO emailSenderDTO) {

        if (emailSenderDTO.getSenderName() == null) {
            emailSenderDTO.setSenderName(" - ");
        }
        if (emailSenderDTO.getSenderEmail() == null) {
            emailSenderDTO.setSenderEmail(" - ");
        }
        String body;
        if (emailSenderDTO.getLanguage() == null || !emailSenderDTO.getLanguage().equalsIgnoreCase("gr")) {
            body = "Email from " + emailSenderDTO.getSenderName() + ". " + DOUBLE_NEW_LINE + "Contact email address: " +
                    emailSenderDTO.getSenderEmail() + ". " + DOUBLE_NEW_LINE + "Message: \n" + emailSenderDTO.getMessage();
        } else {
            body = "Ηλεκτρονικό μήνυμα από " + emailSenderDTO.getSenderName() + ". " + DOUBLE_NEW_LINE + "Διεύθυνση email: " +
                    emailSenderDTO.getSenderEmail() + ". " + DOUBLE_NEW_LINE + "Μήνυμα: \n" + emailSenderDTO.getMessage();
        }
    
        emailRestApiService.sendMailWithWebClient(new MailDTO(emailSenderDTO.getSubject(), body,
                emailSenderDTO.getReceiver()));
    }
    
    
    
    public EmailSenderDTO sendResetPassEmail(EmailSenderDTO emailSenderDTO) {
        if (userService.checkForEntity(userRepository.findDistinctByUserName(emailSenderDTO.getUserName()).getId()).isPresent()) {
            User userToChange = userRepository.findDistinctByUserName(emailSenderDTO.getUserName());
            String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]}\\|;:<>/?";
            char[] possibleCharacters = (allCharacters).toCharArray();
            String randomStr = RandomStringUtils.random(8, 0, possibleCharacters.length - 1, false, false,
                    possibleCharacters, new SecureRandom());
            String subject;
            String body;
            if (emailSenderDTO.getLanguage() == null || !emailSenderDTO.getLanguage().equalsIgnoreCase("gr")) {
                subject = "Reset Password";
                body = buildMessage(userToChange, randomStr);
            } else {
                subject = "Επαναφορά Κωδικού";
                body = buildMessageAlt(userToChange, randomStr);
            }
    
            emailRestApiService.sendMailWithWebClient(new MailDTO(subject, body, emailSenderDTO.getReceiver()));
    
            if (!userToChange.getUserName().equals(GUEST_USER)) {
                userToChange.setPassword(randomStr);
                userRepository.save(userToChange);
            }
            
            return emailSenderDTO;
        } else {
            return null;
        }
    }

    private String buildMessage(User user, String randomStr) {
        if (!user.getUserName().equals(GUEST_USER)) {
            return "Your new password is " + randomStr + ". It is recommended to change this password immediately" +
                    " after next connection.";
        } else {
            return "Your new password would be " + randomStr + ". \nSince this is the guest user password " +
                    "will remain the same (guest).";
        }
    }

    private String buildMessageAlt(User user, String randomStr) {
        if (!user.getUserName().equals(GUEST_USER)) {
            return "Ο νέος σας κωδικός είναι " + randomStr + ". Συνιστάται να αλλάξετε αυτόν τον κωδικό αμέσως " +
                    "μετά την επόμενη σύνδεση.";
        } else {
            return "Ο νέος σας κωδικός θα ήταν " + randomStr + ". \nΕπειδή πρόκειται για τον χρήστη guest ο " +
                    "κωδικός θα παραμείνει ο ίδιος (guest).";
        }
    }
}
