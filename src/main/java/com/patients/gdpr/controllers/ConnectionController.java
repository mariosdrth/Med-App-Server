package com.patients.gdpr.controllers;

import com.patients.gdpr.dto.ConnectionDTO;
import com.patients.gdpr.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/connections")
public class ConnectionController {
    
    @Autowired
    ConnectionService connectionService;
    
    @GetMapping
    @ResponseBody
    public List<ConnectionDTO> getAllConnections() {
        return connectionService.getAllConnections();
    }
    
    @PostMapping(value = "/new")
    @ResponseBody
    public ConnectionDTO createConnection(@RequestBody ConnectionDTO connectionDTO) {
        return connectionService.createEntity(connectionDTO);
    }
    
}
