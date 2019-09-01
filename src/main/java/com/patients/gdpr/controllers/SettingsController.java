package com.patients.gdpr.controllers;

import com.patients.gdpr.dto.SettingsDTO;
import com.patients.gdpr.services.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "/settings")
public class SettingsController {
    
    @Autowired
    SettingsService settingsService;
    
    @GetMapping(value = "/{id}")
    @ResponseBody
    public SettingsDTO getUserSettings(@PathVariable("id") BigInteger id) {
        return settingsService.getUserSettings(id);
    }
    
    @PostMapping(value = "/new")
    @ResponseBody
    public SettingsDTO createSettings(@RequestBody SettingsDTO settingsDTO) {
        return settingsService.createEntity(settingsDTO);
    }
    
    @PutMapping(value = "/{id}")
    @ResponseBody
    public SettingsDTO updateSettings(@PathVariable("id") BigInteger id, @RequestBody SettingsDTO settingsDTO) {
        return settingsService.updateEntity(id, settingsDTO);
    }
}
