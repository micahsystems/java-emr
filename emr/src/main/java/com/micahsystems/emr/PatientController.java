package com.micahsystems.emr;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    private final PatientRepository patientRepository;
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
}