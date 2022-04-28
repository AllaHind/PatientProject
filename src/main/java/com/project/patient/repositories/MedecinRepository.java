package com.project.patient.repositories;

import com.project.patient.entities.Medecin;
import com.project.patient.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    @Query("Select m from Medecin m where m.codeMedecin LIKE %:keyword% ")
    Page<Medecin> findbyCriteria(@Param("keyword") String keyword, Pageable pageable);
}
