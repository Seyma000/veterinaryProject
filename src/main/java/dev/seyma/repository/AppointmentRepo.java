package dev.seyma.repository;

import dev.seyma.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDateTime(LocalDateTime localDateTime);
    List<Appointment> findByDoctorIdAndDateTimeBetween(int id, LocalDateTime entryDate, LocalDateTime exitDate);
    List<Appointment> findByAnimalIdAndDateTimeBetween(int id, LocalDateTime entryDate, LocalDateTime exitDate);
    Optional<Appointment> findByDateTimeAndDoctorIdAndAnimalId(LocalDateTime dateTime, Integer doctorId, Integer animalId);
}
