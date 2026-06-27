package com.micahsystems.emr;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Validated
@Valid
@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    // GET search patients by last name
    @GetMapping("/search")
    public List<Patient> searchPatients(@RequestParam String lastName) {
        return patientRepository.findByLastNameContainingIgnoreCase(lastName);
    }
    // GET one patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // POST create a new patient
    @PostMapping
    public Patient createPatient(@Valid @RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
    // PUT update an existing patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@Valid @PathVariable Long id, @RequestBody Patient updated) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setFirstName(updated.getFirstName());
                    patient.setLastName(updated.getLastName());
                    patient.setDateOfBirth(updated.getDateOfBirth());
                    patient.setPhone(updated.getPhone());
                    patient.setEmail(updated.getEmail());
                    return ResponseEntity.ok(patientRepository.save(patient));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    // DELETE a patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}