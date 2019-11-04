package com.patients.gdpr.services;

import com.patients.gdpr.dto.UserDTO;
import com.patients.gdpr.dto.UserSearch;
import com.patients.gdpr.model.User;
import com.patients.gdpr.repositories.UserRepository;
import com.patients.gdpr.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    public List<UserDTO> searchUsers(UserSearch userSearch) {
        List<UserDTO> userDTOList = new ArrayList<>();
        userRepository.searchUsers(userSearch).forEach(user -> userDTOList.add(new UserDTO(user)));
        return userDTOList;
    }
    
    public List<UserDTO> createEntity(List<UserDTO> userDTOList) {
        for (UserDTO userDTO : userDTOList) {
            User user = userRepository.save(DTOToEntity(userDTO));
            userDTO.setId(user.getId());
            userDTO.setUserRole(userRoleRepository.getOne(user.getUserRoleId()));
        }
        return userDTOList;
    }
    
    public UserDTO loginUser(UserDTO userDTO) {
        User userToFind = userRepository.findDistinctByUserName(userDTO.getUserName());
        if (userToFind != null) {
            UserDTO userDTONew = new UserDTO();
            if (userDTO.getPassword().equals(userToFind.getPassword())) {
                userDTONew.setUserName(userToFind.getUserName());
                userDTONew.setId(userToFind.getId());
                userDTONew.setUserRoleId(userToFind.getUserRoleId());
                userDTONew.setUserRole(userToFind.getUserRole());
                return userDTONew;
            } else {
                return null;
            }
        }
        else {
            return null;
        }
    }
    
    public UserDTO updateEntity(BigInteger id, UserDTO userDTO) {
        userDTO.setId(id);
        if (userDTO.getPassword().equals("") || userDTO.getPassword() == null) {
            userDTO.setPassword(userRepository.findDistinctByUserName(userDTO.getUserName()).getPassword());
        }
        UserDTO userDTONew = entityToDTO(userRepository.save(DTOToEntity(userDTO)));
        userDTONew.setPassword(null);
        return userDTONew;
    }
    
    public UserDTO showDetails(BigInteger id) {
        return entityToDTO(userRepository.getOne(id));
    }
    
    public void deleteEntity(BigInteger id) {
        userRepository.delete(userRepository.getOne(id));
    }
    
    public Optional<User> checkForEntity(BigInteger id) {
        return userRepository.findById(id);
    }
    
    public UserDTO entityToDTO(User user) {
        return new UserDTO(user);
    }
    
    public User DTOToEntity(UserDTO userDTO) {
        return new User(userDTO);
    }
    
}
