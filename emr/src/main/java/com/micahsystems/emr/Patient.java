package com.micahsystems.emr;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "patients")
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstName;
        private String lastName;
        private String phone;
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

