package com.patients.gdpr.repositories;

import com.patients.gdpr.dto.SessionSearch;
import com.patients.gdpr.model.Session;

import java.util.List;

public interface SessionCustom {
    
    List<Session> searchSessions(SessionSearch sessionSearch);
    List<Session> searchSessionsNoLimit(SessionSearch sessionSearch);
    
}
