package com.micahsystems.emr;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be under 50 characters")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be under 50 characters")
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "Date of birth is required")
    @JsonProperty("dateOfBirth")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Size(max = 20, message = "Phone must be under 20 characters")
    private String phone;
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email must be under 100 characters")
    private String email;
    public Patient() {
    }
    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    }

