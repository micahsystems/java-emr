package com.micahsystems.emr;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/encounters")
public class EncounterController {
    private final EncounterRepository encounterRepository;
    private final PatientRepository patientRepository;
    public EncounterController(EncounterRepository encounterRepository,
                               PatientRepository patientRepository) {
        this.encounterRepository = encounterRepository;
        this.patientRepository = patientRepository;
    }
    // GET all encounters
    @GetMapping
    public List<Encounter> getAllEncounters() {
        return encounterRepository.findAll();
    }
    // GET all encounters for a specific patient
    @GetMapping("/patient/{patientId}")
    public List<Encounter> getEncountersByPatient(@PathVariable Long patientId) {
        return encounterRepository.findByPatientId(patientId);
    }
    // POST create a new encounter
    @PostMapping
    public ResponseEntity<Encounter> createEncounter(@RequestBody EncounterRequest request) {
        return patientRepository.findById(request.getPatientId())
                .map(patient -> {
                    Encounter encounter = new Encounter();
                    encounter.setPatient(patient);
                    encounter.setVisitDate(request.getVisitDate());
                    encounter.setReason(request.getReason());
                    encounter.setNotes(request.getNotes());
                    return ResponseEntity.ok(encounterRepository.save(encounter));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    // DELETE an encounter
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncounter(@PathVariable Long id) {
        if (encounterRepository.existsById(id)) {
            encounterRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
