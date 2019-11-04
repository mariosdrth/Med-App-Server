package com.patients.gdpr.controllers;

import com.patients.gdpr.dto.UserDTO;
import com.patients.gdpr.dto.UserSearch;
import com.patients.gdpr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    @ResponseBody
    public List<UserDTO> showUsers(@RequestBody UserSearch userSearch) {
        return userService.searchUsers(userSearch);
    }
    
    @PostMapping(value = "/new")
    @ResponseBody
    public List<UserDTO> createUser(@RequestBody List<UserDTO> userDTOList) {
        return userService.createEntity(userDTOList);
    }
    
    @GetMapping(value = "/{id}")
    @ResponseBody
    public UserDTO showUserDetails(@PathVariable("id") BigInteger id) {
        if (userService.checkForEntity(id).isPresent()) {
            return userService.showDetails(id);
        } else {
            return null;
        }
    }
    
    @PostMapping(value = "/login")
    @ResponseBody
    public UserDTO loginUser(@RequestBody UserDTO userDTO) {
        return userService.loginUser(userDTO);
    }
    
    @PutMapping(value = "/{id}")
    @ResponseBody
    public UserDTO updateUser(@PathVariable("id") BigInteger id, @RequestBody UserDTO userDTO) {
        if (userService.checkForEntity(id).isPresent()) {
            return userService.updateEntity(id, userDTO);
        } else {
            return null;
        }
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") BigInteger id) {
        if (userService.checkForEntity(id).isPresent()) {
            userService.deleteEntity(id);
        }
    }
    
    
}
