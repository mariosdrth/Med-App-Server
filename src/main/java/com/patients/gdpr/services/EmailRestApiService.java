package com.patients.gdpr.services;

import com.patients.gdpr.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class EmailRestApiService {
    
    @Autowired
    private Environment env;
    
    public void sendMailWithWebClient(MailDTO mailDTO) {
        WebClient webClient = WebClient.create(Objects.requireNonNull(env.getProperty("email.rest.api.url")));
        webClient.post().uri("/send-email").body(BodyInserters.fromObject(mailDTO)).exchange().subscribe();
    }
}
