package com.project.patient;

import com.project.patient.entities.Patient;
import com.project.patient.repositories.PatientRepository;
import com.project.patient.sec.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientApplication {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }

   @Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
          //  securityService.saveNewUser("Hindou", "1234", "1234");

         // securityService.saveNewRole("USER", "Utilisateur");

          //  securityService.addRoleToUser("Hindou","ADMIN");
           // securityService.addRoleToUser("Hindou","USER");
        };
    }


}
