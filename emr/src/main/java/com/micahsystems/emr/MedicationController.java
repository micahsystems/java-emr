package com.micahsystems.emr;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/medications")
public class MedicationController {
    private final MedicationRepository medicationRepository;
    private final PatientRepository patientRepository;
    public MedicationController(MedicationRepository medicationRepository,
                                PatientRepository patientRepository) {
        this.medicationRepository = medicationRepository;
        this.patientRepository = patientRepository;
    }
    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
    @GetMapping("/patient/{patientId}")
    public List<Medication> getMedicationsByPatient(@PathVariable Long patientId) {
        return medicationRepository.findByPatientId(patientId);
    }
    @PostMapping
    public ResponseEntity<Medication> createMedication(@RequestBody MedicationRequest request) {
        return patientRepository.findById(request.getPatientId())
                .map(patient -> {
                    Medication medication = new Medication();
                    medication.setPatient(patient);
                    medication.setName(request.getName());
                    medication.setDosage(request.getDosage());
                    medication.setFrequency(request.getFrequency());
                    medication.setStartDate(request.getStartDate());
                    medication.setEndDate(request.getEndDate());
                    return ResponseEntity.ok(medicationRepository.save(medication));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        if (medicationRepository.existsById(id)) {
            medicationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
