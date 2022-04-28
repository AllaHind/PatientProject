package com.project.patient.sec.service;

import com.project.patient.sec.entities.AppRole;
import com.project.patient.sec.entities.AppUser;
import com.project.patient.sec.repositories.AppRoleRepository;
import com.project.patient.sec.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
   private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username,String password,String verifypwd) {

       if(!password.equals(verifypwd)) throw  new RuntimeException("Passwords are not matching");
       String hashedPwd=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPwd);
        appUser.setActive(true);
       AppUser saveduser= appUserRepository.save(appUser);
        return saveduser;



    }
    @Override
    public AppRole saveNewRole(String role_name, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(role_name);
        if(appRole!=null) throw  new RuntimeException("Role"+role_name+"already exists");
        appRole=new AppRole();
        appRole.setRoleName(role_name);
        appRole.setDescription(description);
        AppRole savedAppRole=appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String role_name) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw  new RuntimeException("Username doesn't exist");
        AppRole appRole = appRoleRepository.findByRoleName(role_name);
        if(appRole==null) throw  new RuntimeException("Role doesn't exist");
         appUser.getAppRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role_name) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw  new RuntimeException("Username doesn't exist");
        AppRole appRole = appRoleRepository.findByRoleName(role_name);
        if(appRole==null) throw  new RuntimeException("Role doesn't exist");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }


}
