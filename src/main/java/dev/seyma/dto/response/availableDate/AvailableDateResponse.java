package dev.seyma.dto.response.availableDate;

import dev.seyma.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {
    private int id;
    private LocalDate dateTime;
    private Doctor doctor;
}
