package com.tibuhealth.Tibu.repository;

import com.tibuhealth.Tibu.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
    //Accepts all crud databases
}
