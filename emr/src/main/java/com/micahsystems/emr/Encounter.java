package com.micahsystems.emr;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "encounters")
public class Encounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;
    @Column(nullable = false)
    private String reason;
    private String notes;
    public Encounter() {
    }
    public Long getId() {
        return id;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
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
