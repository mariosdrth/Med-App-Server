package com.patients.gdpr.repositories;

import com.patients.gdpr.dto.UserSearch;
import com.patients.gdpr.model.User;

import java.util.List;

public interface UserCustom {
    
    List<User> searchUsers(UserSearch userSearch);
    
}
