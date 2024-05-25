package dev.seyma.dto.response.vaccine;

import dev.seyma.entity.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineResponse {
    private int id;
    private String name;
    private String code;
    private LocalDate protectionStrtDate;
    private LocalDate protectionFnshDate;
    private Integer animalId;
}
