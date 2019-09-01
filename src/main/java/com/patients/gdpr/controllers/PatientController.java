package com.patients.gdpr.controllers;

import com.patients.gdpr.dto.PatientDTO;
import com.patients.gdpr.dto.PatientSearch;
import com.patients.gdpr.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {
    
    @Autowired
    private PatientService patientService;
    
    @GetMapping
    @ResponseBody
    public Long getSizeOfPatients() {
        return patientService.getSizeOfPatients();
    }
    
    @PostMapping
    @ResponseBody
    //@PreAuthorize("hasRole('ADMIN')")
    public List<PatientDTO> showPatients(@RequestBody PatientSearch patientSearch) {
        return patientService.searchPatients(patientSearch);
    }
    
    @GetMapping(value = "/all")
    @ResponseBody
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }
    
    @PostMapping(value = "/new")
    @ResponseBody
    public List<PatientDTO> createPatient(@RequestBody List<PatientDTO> patientDTOList) {
        return patientService.createEntity(patientDTOList);
    }
    
    @GetMapping(value = "/{id}")
    @ResponseBody
    public PatientDTO showPatientDetails(@PathVariable("id") BigInteger id) {
        if (patientService.checkForEntity(id).isPresent()) {
            return patientService.showDetails(id);
        } else {
            return null;
        }
    }
    
    @PutMapping(value = "/{id}")
    @ResponseBody
    public PatientDTO updatePatient(@PathVariable("id") BigInteger id, @RequestBody PatientDTO patientDTO) {
        if (patientService.checkForEntity(id).isPresent()) {
            return patientService.updateEntity(id, patientDTO);
        } else {
            return null;
        }
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deletePatient(@PathVariable("id") BigInteger id) {
        if (patientService.checkForEntity(id).isPresent()) {
            patientService.deleteEntity(id);
        }
    }
}
