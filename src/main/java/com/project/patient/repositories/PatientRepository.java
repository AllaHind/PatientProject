package com.project.patient.repositories;

import com.project.patient.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {


    List<Patient> findByMaladeAndScoreGreaterThan(boolean m, int score);

    @Query("Select p from Patient p where p.nom LIKE %:keyword% ")
    Page<Patient> findbyCriteria(@Param("keyword") String keyword, Pageable pageable);

}