package com.patients.gdpr.services;

import com.patients.gdpr.dto.UserRoleDTO;
import com.patients.gdpr.dto.UserRoleSearch;
import com.patients.gdpr.model.UserRole;
import com.patients.gdpr.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    public List<UserRoleDTO> searchUsers(UserRoleSearch userRoleSearch) {
        List<UserRoleDTO> userRoleDTOList = new ArrayList<>();
        userRoleRepository.searchUserRoles(userRoleSearch).forEach(userRole -> userRoleDTOList.add(new UserRoleDTO(userRole)));
        return userRoleDTOList;
    }
    
    public List<UserRoleDTO> createEntity(List<UserRoleDTO> userRoleDTOList) {
        for (UserRoleDTO userRoleDTO : userRoleDTOList) {
            UserRole userRole = userRoleRepository.save(DTOToEntity(userRoleDTO));
            userRoleDTO.setId(userRole.getId());
        }
        return userRoleDTOList;
    }
    
    public UserRoleDTO updateEntity(BigInteger id, UserRoleDTO userRoleDTO) {
        userRoleDTO.setId(id);
        return entityToDTO(userRoleRepository.save(DTOToEntity(userRoleDTO)));
    }
    
    public UserRoleDTO showDetails(BigInteger id) {
        return entityToDTO(userRoleRepository.getOne(id));
    }
    
    public void deleteEntity(BigInteger id) {
        userRoleRepository.delete(userRoleRepository.getOne(id));
    }
    
    public Optional<UserRole> checkForEntity(BigInteger id) {
        return userRoleRepository.findById(id);
    }
    
    public UserRoleDTO entityToDTO(UserRole userRole) {
        return new UserRoleDTO(userRole);
    }
    
    public UserRole DTOToEntity(UserRoleDTO userRoleDTO) {
        return new UserRole(userRoleDTO);
    }
    
    
}
