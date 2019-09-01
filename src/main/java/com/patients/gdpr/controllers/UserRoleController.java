package com.patients.gdpr.controllers;

import com.patients.gdpr.dto.UserRoleDTO;
import com.patients.gdpr.dto.UserRoleSearch;
import com.patients.gdpr.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/users/roles")
public class UserRoleController {
    
    @Autowired
    private UserRoleService userRoleService;
    
    @PostMapping
    @ResponseBody
    public List<UserRoleDTO> showUserRoles(@RequestBody UserRoleSearch userRoleSearch) {
        return userRoleService.searchUsers(userRoleSearch);
    }
    
    @PostMapping(value = "/new")
    @ResponseBody
    public List<UserRoleDTO> createUserRole(@RequestBody List<UserRoleDTO> userRoleDTOList) {
        return userRoleService.createEntity(userRoleDTOList);
    }
    
    @GetMapping(value = "/{id}")
    @ResponseBody
    public UserRoleDTO showUserRoleDetails(@PathVariable("id") BigInteger id) {
        if (userRoleService.checkForEntity(id).isPresent()) {
            return userRoleService.showDetails(id);
        } else {
            return null;
        }
    }
    
    @PutMapping(value = "/{id}")
    @ResponseBody
    public UserRoleDTO updateUserRole(@PathVariable("id") BigInteger id, @RequestBody UserRoleDTO userRoleDTO) {
        if (userRoleService.checkForEntity(id).isPresent()) {
            return userRoleService.updateEntity(id, userRoleDTO);
        } else {
            return null;
        }
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteUserRole(@PathVariable("id") BigInteger id) {
        if (userRoleService.checkForEntity(id).isPresent()) {
            userRoleService.deleteEntity(id);
        }
    }
    
}
