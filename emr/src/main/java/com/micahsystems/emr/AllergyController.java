package com.micahsystems.emr;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/allergies")
public class AllergyController {
    private final AllergyRepository allergyRepository;
    private final PatientRepository patientRepository;
    public AllergyController(AllergyRepository allergyRepository,
                             PatientRepository patientRepository) {
        this.allergyRepository = allergyRepository;
        this.patientRepository = patientRepository;
    }
    @GetMapping
    public List<Allergy> getAllAllergies() {
        return allergyRepository.findAll();
    }
    @GetMapping("/patient/{patientId}")
    public List<Allergy> getAllergiesByPatient(@PathVariable Long patientId) {
        return allergyRepository.findByPatientId(patientId);
    }
    @PostMapping
    public ResponseEntity<Allergy> createAllergy(@RequestBody AllergyRequest request) {
        return patientRepository.findById(request.getPatientId())
                .map(patient -> {
                    Allergy allergy = new Allergy();
                    allergy.setPatient(patient);
                    allergy.setAllergen(request.getAllergen());
                    allergy.setReaction(request.getReaction());
                    allergy.setSeverity(request.getSeverity());
                    return ResponseEntity.ok(allergyRepository.save(allergy));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAllergy(@PathVariable Long id) {
        if (allergyRepository.existsById(id)) {
            allergyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
