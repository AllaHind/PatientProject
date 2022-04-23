package com.project.patient;

import com.project.patient.entities.Patient;
import com.project.patient.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientApplication  {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }

   /* @Override
    public void run(String... args) throws Exception {

//Save Patient
        for (int i = 0; i < 10; i++) {
            patientRepository.save(new Patient(null, "Hind", new Date(), false, (int) (100 * Math.random())));

        }
//Consulter Un patient


        Patient patient = patientRepository.findById(3L).orElse(null);
        System.out.println("Consulter le patient  :");
        System.out.println(patient.getNom());
        //Afficher Liste des patients
        Page<Patient> all = patientRepository.findAll(PageRequest.of(0, 5));
        System.out.println(all.getTotalElements());
        System.out.println(all.getTotalPages());
        System.out.println(all.getNumber());
        for (Patient patients : all) {
            System.out.println(patients.getNom());
            System.out.println(patients.getDateNaissance());
            System.out.println(patients.getScore());
        }

        patient.setNom("ALLA");
        patientRepository.save(patient);
        System.out.println(patient);
        patientRepository.deleteById(2L);


        List<Patient> find = patientRepository.findByMaladeAndScoreGreaterThan(false, 30);
        System.out.println(find);
    }*/
}
