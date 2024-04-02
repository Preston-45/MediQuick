package com.tibuhealth.Tibu.service;

import com.tibuhealth.Tibu.exception.ResourceNotFoundException;
import com.tibuhealth.Tibu.model.Medication;
import com.tibuhealth.Tibu.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    //create a method that returns a list of medication
    public List<Medication> getAllMedication(){
        //creating a List
        List<Medication> medications = new ArrayList<>();
        medicationRepository.findAll().forEach(medications::add);
        return medications;
    }

    //get a medication
    public Optional<Medication> getMedication(Long id){
        Optional<Medication> optionalMedication = medicationRepository.findById(id);
        // Check if optional medication contains a value, and return if it present
        if (optionalMedication.isPresent()){
            return Optional.of(optionalMedication.get());
        }else {
            //Handle the case where the medication with the given ID was not found
            // here we could throw an exceptional or return null
            return null;
        }
    }

    // Add a Medication
    public Medication addMedication(Medication medication){
        medicationRepository.save(medication);
        return medication;
    }

    public void updateMedication(Long id, Medication updatedMedication){
        // Retrieve the existing medication  from the database by ID
        Optional<Medication> optionalMedication = medicationRepository.findById(id);
        if (optionalMedication.isPresent()){
            //Get the existing medication
            Medication existingMedication = optionalMedication.get();
            // update the properties of the existing medics with value from the updated medics
            existingMedication.setName(updatedMedication.getName());
            existingMedication.setDescription(updatedMedication.getDescription());
            existingMedication.setPrice(updatedMedication.getPrice());
            existingMedication.setStock_quantity(updatedMedication.getStock_quantity());
            // Save the updated medication to the database
            medicationRepository.save(existingMedication);
        } else {
            // Handle the case where the medication with the given ID was not found
            throw new ResourceNotFoundException("Medication not found with id:" + id);
        }
    }

    public Medication deleteMedication(Medication id){
        medicationRepository.delete(id);
        return id;
    }
}
