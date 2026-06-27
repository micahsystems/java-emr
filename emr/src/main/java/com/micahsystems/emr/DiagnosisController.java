package com.micahsystems.emr;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/diagnoses")
public class DiagnosisController {
    private final DiagnosisRepository diagnosisRepository;
    private final EncounterRepository encounterRepository;
    public DiagnosisController(DiagnosisRepository diagnosisRepository,
                               EncounterRepository encounterRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.encounterRepository = encounterRepository;
    }
    @GetMapping
    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisRepository.findAll();
    }
    @GetMapping("/encounter/{encounterId}")
    public List<Diagnosis> getDiagnosesByEncounter(@PathVariable Long encounterId) {
        return diagnosisRepository.findByEncounterId(encounterId);
    }
    @PostMapping
    public ResponseEntity<Diagnosis> createDiagnosis(@RequestBody DiagnosisRequest request) {
        return encounterRepository.findById(request.getEncounterId())
                .map(encounter -> {
                    Diagnosis diagnosis = new Diagnosis();
                    diagnosis.setEncounter(encounter);
                    diagnosis.setIcd10Code(request.getIcd10Code());
                    diagnosis.setDescription(request.getDescription());
                    diagnosis.setDiagnosedDate(request.getDiagnosedDate());
                    return ResponseEntity.ok(diagnosisRepository.save(diagnosis));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnosis(@PathVariable Long id) {
        if (diagnosisRepository.existsById(id)) {
            diagnosisRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
