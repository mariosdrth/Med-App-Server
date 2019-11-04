package com.patients.gdpr.services;

import com.patients.gdpr.dto.SettingsDTO;
import com.patients.gdpr.model.Settings;
import com.patients.gdpr.repositories.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SettingsService {
    
    @Autowired
    SettingsRepository settingsRepository;
    
    public SettingsDTO getUserSettings(BigInteger userId) {
        if (checkForEntity(userId)) {
            return entityToDTO(settingsRepository.getDistinctByUserId(userId));
        } else {
            return null;
        }
    }
    
    public SettingsDTO createEntity(SettingsDTO settingsDTO) {
        Settings settings = settingsRepository.save(DTOToEntity(settingsDTO));
        settingsDTO.setId(settings.getId());
        return settingsDTO;
    }
    
    public SettingsDTO updateEntity(BigInteger id, SettingsDTO settingsDTO) {
        settingsDTO.setId(id);
        return entityToDTO(settingsRepository.save(DTOToEntity(settingsDTO)));
    }
    
    public SettingsDTO entityToDTO(Settings settings) {
        return new SettingsDTO(settings);
    }
    
    public Settings DTOToEntity(SettingsDTO settingsDTO) {
        return new Settings(settingsDTO);
    }
    
    public boolean checkForEntity(BigInteger userId) {
        try {
            return settingsRepository.getDistinctByUserId(userId).getId() != null;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
