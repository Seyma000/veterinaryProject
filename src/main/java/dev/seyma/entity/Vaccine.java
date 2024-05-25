package dev.seyma.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id", columnDefinition = "serial")
    private int id;

    @Column(name = "vaccine_name")
    private String name;
    @Column(name = "vaccine_code")
    private String code;
    @Column(name = "vaccine_protectionStrtDate")
    private LocalDate protectionStrtDate;
    @Column(name = "vaccine_protectionFnshDate")
    private LocalDate protectionFnshDate;

    @ManyToOne()
    @JoinColumn(name = "vaccine_animal_id", referencedColumnName = "animal_id")
    private Animal animal;
}
