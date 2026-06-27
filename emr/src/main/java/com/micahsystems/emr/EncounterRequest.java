package com.micahsystems.emr;
import java.time.LocalDate;
public class EncounterRequest {
    private Long patientId;
    private LocalDate visitDate;
    private String reason;
    private String notes;
    public Long getPatientId() {
        return patientId;
    }
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    public LocalDate getVisitDate() {
        return visitDate;
    }
    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
