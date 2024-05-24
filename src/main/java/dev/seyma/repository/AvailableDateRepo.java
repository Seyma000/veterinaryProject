package dev.seyma.repository;

import dev.seyma.entity.AvailableDate;
import dev.seyma.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Integer> {
    List<AvailableDate> findByDateAndDoctor(LocalDate availableDate, Doctor doctor);

}
