package dev.seyma.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "availableDates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availableDate_id", columnDefinition = "serial")
    private int id;

    @NotNull
    @Column(name = "availableDate_date")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "availableDate_doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
}
