package com.project.patient.sec.service;

import com.project.patient.sec.entities.AppRole;
import com.project.patient.sec.entities.AppUser;

public interface SecurityService {

    AppUser saveNewUser(String username,String password,String verifypwd);
    AppRole saveNewRole(String role_name, String description);
    void addRoleToUser(String username,String role_name);
    void removeRoleFromUser(String username,String role_name);
    AppUser loadUserByUsername(String username);
}
