package com.patients.gdpr.repositories;

import com.patients.gdpr.dto.UserRoleSearch;
import com.patients.gdpr.model.UserRole;

import java.util.List;

public interface UserRoleCustom {
    
    List<UserRole> searchUserRoles(UserRoleSearch userRoleSearch);
    
}
