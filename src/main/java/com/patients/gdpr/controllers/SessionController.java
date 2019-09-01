package com.patients.gdpr.controllers;

import com.patients.gdpr.dto.SessionDTO;
import com.patients.gdpr.dto.SessionSearch;
import com.patients.gdpr.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {
    
    @Autowired
    private SessionService sessionService;
    
    @GetMapping
    @ResponseBody
    public Long getSizeOfSessions() {
        return sessionService.getSizeOfSessions();
    }
    
    @PostMapping
    @ResponseBody
    public List<SessionDTO> showSessions(@RequestBody SessionSearch sessionSearch) {
        return sessionService.searchSessions(sessionSearch);
    }
    
    @PostMapping(value = "/all")
    @ResponseBody
    public List<SessionDTO> showSessionsAll(@RequestBody SessionSearch sessionSearch) {
        return sessionService.searchSessionsAll(sessionSearch);
    }
    
    @PostMapping(value = "/new")
    @ResponseBody
    public List<SessionDTO> createSession(@RequestBody List<SessionDTO> sessionDTOList) {
        return sessionService.createEntity(sessionDTOList);
    }
    
    @GetMapping(value = "/{id}")
    @ResponseBody
    public SessionDTO showSessionDetails(@PathVariable("id") BigInteger id) {
        if (sessionService.checkForEntity(id).isPresent()) {
            return sessionService.showDetails(id);
        } else {
            return null;
        }
    }
    
    @PutMapping(value = "/{id}")
    @ResponseBody
    public SessionDTO updateSession(@PathVariable("id") BigInteger id, @RequestBody SessionDTO sessionDTO) {
        if (sessionService.checkForEntity(id).isPresent()) {
            return sessionService.updateEntity(id, sessionDTO);
        } else {
            return null;
        }
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteSession(@PathVariable("id") BigInteger id) {
        if (sessionService.checkForEntity(id).isPresent()) {
            sessionService.deleteEntity(id);
        }
    }
    
}
